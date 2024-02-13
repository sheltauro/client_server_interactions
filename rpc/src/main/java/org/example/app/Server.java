package org.example.app;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;

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

    static Double doReflection(RPCRegistry registry) {
        double result = 0.0;
        try {
            // Reflection code.
            Class<?> cls = Class.forName("org.example.app.RunFunction");

            Method method = cls.getDeclaredMethod(registry.getName(), Number.class);
            result = Double.parseDouble(
                    String.valueOf(
                            method.invoke(null, (Number) registry.getParameters().get(0))
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

            while (true) {
                RPCRegistry registry = (RPCRegistry) in.readObject();
                out.println(doReflection(registry));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
