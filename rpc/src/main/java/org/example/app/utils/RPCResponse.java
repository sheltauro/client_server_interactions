package org.example.app.utils;

import java.io.Serializable;
import java.util.UUID;

public class RPCResponse implements Serializable {
    private RPCStatus status;

    private Object response;

    private String errorMessage;

    private UUID uuid;

    // Only for testing.
    public RPCResponse(RPCStatus status, Object result) {
        this.status = status;
        this.response = result;
        this.errorMessage = "";
    }

    public RPCResponse(RPCStatus status, Object result, UUID uuid) {
        this.status = status;
        this.response = result;
        this.errorMessage = "";
        this.uuid = uuid;
    }

    public RPCResponse(RPCStatus status, Object result, String errorMessage) {
        this.status = status;
        this.response = result;
        this.errorMessage = errorMessage;
    }

    public RPCResponse(RPCStatus status, Object result, String errorMessage, UUID uuid) {
        this.status = status;
        this.response = result;
        this.errorMessage = errorMessage;
        this.uuid = uuid;
    }

    public RPCStatus getStatus() {
        return status;
    }

    public Object getResponse() {
        return response;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        if (status == RPCStatus.FAILED) {
            return "RPCResponse{" +
                    "status=" + status +
                    ", errorMessage='" + errorMessage + '\'' +
                    '}';
        }
        return "RPCResponse{" +
                "status=" + status +
                ", response=" + response +
                '}';
    }
}

