package org.example.app;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class RPCUtils {
    // Read the server's response.
    static void readServerResponse(ObjectInputStream in) {
        new Thread(() -> {
            while (true) {
                RPCResponse rpcResponse;
                try {
                    while (true) {
                        rpcResponse = (RPCResponse) in.readObject();
                        System.out.println("echo: " + rpcResponse.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // Verify that a method exists in the RPCRegistry class.
    static Class[] verifyMethodExists(String methodName, Object... parameters) {
        Class<RPCRegistry> cls = RPCRegistry.class;
        try {
            Class[] parameterTypes = new Class[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Number) {
                    parameterTypes[i] = Number.class;
                } else if (parameters[i] instanceof String) {
                    parameterTypes[i] = String.class;
                } else {
                    parameterTypes[i] = parameters[i].getClass();
                }
            }
            cls.getDeclaredMethod(methodName, parameterTypes);
            return parameterTypes;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create an RPCRegistry object.
    static RPCRegistry callMethod(String methodName, Object... parameters) {
        Class[] parameterTypes = verifyMethodExists(methodName, parameters);

        return new RPCRegistry(
                methodName,
                parameters.length,
                parameterTypes,
                new ArrayList<>(List.of(parameters)).toArray(),
                RPCResponse.class.getName()
        );
    }
}
