package org.example.app;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
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
        try {
            // Reflection code.
            Class<?> cls = RPCRegistry.class;

            Method method = cls.getDeclaredMethod(registry.getName(), registry.getParameterTypes());
            response = (RPCResponse) method.invoke(null, registry.getParameters());
            out.writeObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                out.writeObject(new RPCResponse(Status.FAILED, 0.0, e.getMessage()));
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
                        break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
