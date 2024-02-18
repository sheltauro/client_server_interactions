package org.example.app;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static void sendMessagesToClient(BufferedReader stdin, PrintWriter out) {
        new Thread(() -> {
            String userInput;
            try {
                while ((userInput = stdin.readLine()) != null) {
                    out.println(userInput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static void doReflection(RPCRegistry registry, ObjectOutputStream out) {
        RPCResponse response;
        UUID uniqueID = registry.getUniqueID();
        try {
            // Reflection code.
            Class<?> cls = RPCRegistry.class;

            Method method = cls.getDeclaredMethod(registry.getName(), registry.getParameterTypes());

            ArrayList<Object> parameters = registry.getParameters();
            parameters.add(uniqueID);
            response = (RPCResponse) method.invoke(null, parameters.toArray());
            out.writeObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                out.writeObject(new RPCResponse(Status.FAILED, 0.0, e.getMessage(), uniqueID));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(8080);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out1 = new PrintWriter(clientSocket.getOutputStream(), true);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ) {
            sendMessagesToClient(stdin, out1);

            // Create a thread pool to handle incoming requests.
            ExecutorService executorService = Executors.newCachedThreadPool(r -> {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            });

            executorService.submit(() -> {
                while (true) {
                    try {
                        RPCRegistry registry = (RPCRegistry) in.readObject();
                        doReflection(registry, out);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
