package org.example.app;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

public class RPCResponse implements Serializable {
    private Status status;

    private Number response;

    private String errorMessage;

    private UUID uuid;

    // Only for testing.
    public RPCResponse(Status status, Number result) {
        this.status = status;
        this.response = result;
        this.errorMessage = "";
    }

    public RPCResponse(Status status, Number result, UUID uuid) {
        this.status = status;
        this.response = result;
        this.errorMessage = "";
        this.uuid = uuid;
    }

    public RPCResponse(Status status, Number result, String errorMessage) {
        this.status = status;
        this.response = result;
        this.errorMessage = errorMessage;
    }

    public RPCResponse(Status status, Number result, String errorMessage, UUID uuid) {
        this.status = status;
        this.response = result;
        this.errorMessage = errorMessage;
        this.uuid = uuid;
    }

    public Status getStatus() {
        return status;
    }

    public Number getResponse() {
        return response;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "RPCResponse{" +
                "status=" + status +
                ", response=" + response +
                '}';
    }
}

