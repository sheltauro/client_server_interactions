package org.example.app;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Client {
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
    
    static void userInput(BufferedReader stdin, ObjectOutputStream out) {
        try {
            String userInput;
            while ((userInput = stdin.readLine()) != null) {
                String[] strings = userInput.split(" ");
                for (String string : strings) {
                    System.out.println(string);
                }
                out.writeObject(callMethod(strings[0], Double.parseDouble(strings[1])));
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static RPCRegistry callMethod(String name, Object... parameters) {
        return new RPCRegistry(name, parameters.length, new ArrayList<>(List.of(parameters)), "int");
    }

    static void testRPCRegistry(BufferedReader in, ObjectOutputStream out) {
        RPCRegistry rpcRegistry = new RPCRegistry("doubled", 1, List.of(2.0), "double");
        try {
            out.writeObject(rpcRegistry);
            out.flush();
            assert(in.readLine().equals("4.0"));
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
            String mode = "test";
            switch (mode) {
                case "interactive":
                    readServerResponse(in);
                    userInput(stdin, out);
                    break;
                case "test":
                    testRPCRegistry(in, out);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid mode: " + mode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
