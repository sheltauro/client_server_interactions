package org.example.app;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.*;

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
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 8080);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String userInput;
            readServerResponse(in);
            while ((userInput = stdin.readLine()) != null) {
                out.println(userInput);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
