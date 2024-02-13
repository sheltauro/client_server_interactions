package org.example.app;

import java.io.Serializable;
import java.util.List;

class RPCRegistry implements Serializable {
    private String name;

    private int numParameters;

    private List<Object> parameters;

    private String returnType;

    RPCRegistry(String name, int numParameters, List<Object> parameters, String returnType) {
        this.name = name;
        this.numParameters = numParameters;
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public int getNumParameters() {
        return numParameters;
    }

    public List<Object> getParameters() {
        return parameters;
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

    static <T extends Number> double doubled(T a) {
        return a.doubleValue() * 2.0;
    }

    static <T extends Number> double tripled(T a) {
        return a.doubleValue() * 3.0;
    }

    static <T extends Number> double halved(T a) {
        return a.doubleValue() / 2.0;
    }
}
