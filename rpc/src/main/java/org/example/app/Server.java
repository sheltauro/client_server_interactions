package org.example.app;

import java.io.*;
import java.net.*;

public class Server {

    static <T extends Number> double doubled(T a) {
        return a.doubleValue() * 2;
    }

    static void func(BufferedReader stdin, PrintWriter out) {
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

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(8080);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ) {
            func(stdin, out);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Number num = 0;
                try {
                    num = doubled(Double.parseDouble(inputLine));
                } catch (NumberFormatException e) {
                    out.println("Invalid input");
                }
                out.println("stupid: " + num);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
