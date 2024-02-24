package org.example.app.utils;

public class AsyncResponse {

    private RPCResponse response;

    AsyncResponse() {}

    AsyncResponse(RPCResponse response) {
        this.response = response;
    }

    public synchronized void setResponse(RPCResponse response) {
        this.response = response;
        notifyAll();
    }

    public synchronized RPCResponse getResponse() throws InterruptedException {
        while (response == null) {
            wait();
        }
        return response;
    }
}
