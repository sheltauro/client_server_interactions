package org.example.app;

import java.io.Serializable;

class RPCRegistry implements Serializable {
    private String rpcName;

    private int numParameters;

    private Class[] parameterTypes;

    private Object[] parameters;

    private String returnType;

    RPCRegistry(String rpcName, int numParameters, Class[] parameterTypes, Object[] parameters, String returnType) {
        this.rpcName = rpcName;
        this.numParameters = numParameters;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public String getName() {
        return rpcName;
    }

    public int getNumParameters() {
        return numParameters;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
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

    static RPCResponse doubled(Number a) {
        return new RPCResponse(Status.SUCCEEDED, a.doubleValue() * 2.0);
    }

    static RPCResponse tripled(Number a) {
        return new RPCResponse(Status.SUCCEEDED, a.doubleValue() * 3.0);
    }

    static RPCResponse halved(Number a) {
        return new RPCResponse(Status.SUCCEEDED, a.doubleValue() / 2.0);
    }

    static RPCResponse add(Number a, Number b) {
        return new RPCResponse(Status.SUCCEEDED, a.doubleValue() + b.doubleValue());
    }

    static RPCResponse squareRoot(Number a) {
        return new RPCResponse(Status.SUCCEEDED, Math.sqrt(a.doubleValue()));
    }
}
