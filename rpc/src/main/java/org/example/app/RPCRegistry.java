package org.example.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

class RPCRegistry implements Serializable {
    private String rpcName;

    private int numParameters;

    private Class[] parameterTypes;

    private ArrayList<Object> parameters;

    private String returnType;

    private UUID uniqueID;

    RPCRegistry(String rpcName, int numParameters, Class[] parameterTypes, ArrayList<Object> parameters, String returnType) {
        this.rpcName = rpcName;
        this.numParameters = numParameters;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
        this.returnType = returnType;
        this.uniqueID = UUID.randomUUID();
    }

    public String getName() {
        return rpcName;
    }

    public int getNumParameters() {
        return numParameters;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public UUID getUniqueID() {
        return uniqueID;
    }

    @Override
    public String toString() {
        return "RPCRegistry{" +
                "rpcName='" + rpcName + '\'' +
                ", numParameters=" + numParameters +
                ", parameterTypes=" + parameterTypes +
                ", parameters=" + parameters +
                ", returnType='" + returnType + '\'' +
                '}';
    }

    static RPCResponse doubled(Number a, UUID uuid) {
        return new RPCResponse(Status.SUCCEEDED, a.doubleValue() * 2.0, uuid);
    }

    static RPCResponse tripled(Number a, UUID uuid) {
        return new RPCResponse(Status.SUCCEEDED, a.doubleValue() * 3.0, uuid);
    }

    static RPCResponse halved(Number a, UUID uuid) {
        return new RPCResponse(Status.SUCCEEDED, a.doubleValue() / 2.0, uuid);
    }

    static RPCResponse add(Number a, Number b, UUID uuid) {
        return new RPCResponse(Status.SUCCEEDED, a.doubleValue() + b.doubleValue(), uuid);
    }

    static RPCResponse squareRoot(Number a, UUID uuid) {
        return new RPCResponse(Status.SUCCEEDED, Math.sqrt(a.doubleValue()), uuid);
    }
}
