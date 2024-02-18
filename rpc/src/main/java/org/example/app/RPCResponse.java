package org.example.app;

import java.io.Serializable;
import java.util.Optional;

public class RPCResponse implements Serializable {
    private Status status;
    private Number response;

    private String errorMessage;

    public RPCResponse(Status status, Number result) {
        this.status = status;
        this.response = result;
        errorMessage = "";
    }

    public RPCResponse(Status status, Number result, String errorMessage) {
        this.status = status;
        this.response = result;
        this.errorMessage = errorMessage;
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

