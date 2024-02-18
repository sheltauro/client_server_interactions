package org.example.app;

public class RPCResponse {
    private Status status;
    private Number response;

    public RPCResponse(Status status, Number result) {
        this.status = status;
        this.response = result;
    }

    public Status getStatus() {
        return status;
    }

    public Number getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "RPCResponse{" +
                "status=" + status +
                ", response=" + response +
                '}';
    }
}

