package org.example.app;

import org.example.app.utils.RPCRegistry;
import org.example.app.utils.RPCUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerHandler implements Runnable {

    private final Socket socket;

    private ObjectOutputStream out;

    private ObjectInputStream in;

    private ExecutorService pool;

    public ServerHandler(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            pool = Executors.newCachedThreadPool(r -> {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("Waiting for incoming requests...");
            try {
                RPCRegistry registry = (RPCRegistry) in.readObject();
                System.out.println("Received request: " + registry);
                RPCUtils.submitTask(pool, registry, out);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
