package org.example.app;

import java.io.Serializable;

class RPCRegistry implements Serializable {
    private String name;

    private int numParameters;

    private Class[] parameterTypes;

    private Object[] parameters;

    private String returnType;

    RPCRegistry(String name, int numParameters, Class[] parameterTypes, Object[] parameters, String returnType) {
        this.name = name;
        this.numParameters = numParameters;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public String getName() {
        return name;
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
        return "RunFunction{" +
                "name='" + name + '\'' +
                ", numParameters=" + numParameters +
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
