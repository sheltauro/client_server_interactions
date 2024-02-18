package org.example.app;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

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

    static void doReflection(RPCRegistry registry, PrintWriter out) {
        RPCResponse response;
        try {
            // Reflection code.
            Class<?> cls = Class.forName("org.example.app.RPCRegistry");

            Method method = cls.getDeclaredMethod(registry.getName(), registry.getParameterTypes());
            response = (RPCResponse) method.invoke(null, registry.getParameters());
            out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e.getMessage());
            out.println("error");
        }
    }

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(8080);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ) {
            sendMessagesToClient(stdin, out);

            // Create a thread pool to handle incoming requests.
            ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                }
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
