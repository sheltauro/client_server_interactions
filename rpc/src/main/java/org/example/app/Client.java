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

    static RPCRegistry callMethod(String name, Object... parameters) {
        return new RPCRegistry(name, parameters.length, new ArrayList<>(List.of(parameters)), "int");
    }

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 8080);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String userInput;
            readServerResponse(in);
            while ((userInput = stdin.readLine()) != null) {
                String[] strings = userInput.split(" ");
                out.writeObject(callMethod(strings[0], Double.parseDouble(strings[1])));
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
