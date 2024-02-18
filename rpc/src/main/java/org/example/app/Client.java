package org.example.app;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Client {

    // Read the server's response.
    static void readServerResponse(BufferedReader in) {
        new Thread(() -> {
            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("echo: " + inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Read user input and send it to the server.
    static void userInput(BufferedReader stdin, ObjectOutputStream out) {
        try {
            String userInput;
            while ((userInput = stdin.readLine()) != null) {
                String[] strings = userInput.split(" ");
                Double[] numbers = new Double[strings.length - 1];
                for (int i = 1; i < strings.length; i++) {
                    numbers[i - 1] = Double.parseDouble(strings[i]);
                }
                out.writeObject(callMethod(strings[0], numbers));
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Verify that a method exists in the RPCRegistry class.
    static Class[] verifyMethodExists(String name, Object... parameters) {
        Class<RPCRegistry> cls = RPCRegistry.class;
        try {
            Class[] parameterTypes = new Class[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Number) {
                    parameterTypes[i] = Number.class;
                } else if (parameters[i] instanceof String) {
                    parameterTypes[i] = String.class;
                } else {
                    parameterTypes[i] = parameters[i].getClass();
                }
            }
            cls.getDeclaredMethod(name, parameterTypes);
            return parameterTypes;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create an RPCRegistry object.
    static RPCRegistry callMethod(String name, Object... parameters) {
        Class[] parameterTypes = verifyMethodExists(name, parameters);

        return new RPCRegistry(
                name,
                parameters.length,
                parameterTypes,
                new ArrayList<>(List.of(parameters)).toArray(),
                RPCResponse.class.getName()
        );
    }

    // Test the RPCRegistry class.
    static void testRPCRegistry(BufferedReader in, ObjectOutputStream out) {
        RPCRegistry rpcRegistry = new RPCRegistry(
                "doubled",
                1,
                new Class[]{Number.class},
                new Object[]{2.0},
                RPCResponse.class.getName()
        );

        try {
            out.writeObject(rpcRegistry);
            out.flush();
            assert (in.readLine().equals(
                    new RPCResponse(Status.SUCCEEDED, 4.0).toString()));
            System.out.println("Test 1 passed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 8080);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String mode = "interactive";

            // Switch between interactive and test mode.
            switch (mode) {
                case "interactive" -> {
                    readServerResponse(in);
                    userInput(stdin, out);
                }
                case "test" -> testRPCRegistry(in, out);
                default -> throw new IllegalArgumentException("Invalid mode: " + mode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
