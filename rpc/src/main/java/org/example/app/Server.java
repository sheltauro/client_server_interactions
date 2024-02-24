package org.example.app;

import org.example.app.utils.RPCUtils;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        ExecutorService pool = RPCUtils.createCachedThreadPool();
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new ServerHandler(socket));
            }
        } catch (Exception ex) {
            pool.shutdown();
        }
    }
}
