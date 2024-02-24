package org.example.app;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        ExecutorService pool = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
        try {
            while (true) {
                pool.execute(new ServerHandler(serverSocket.accept()));
            }
        } catch (Exception ex) {
            pool.shutdown();
        }
    }
}
