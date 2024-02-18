package org.example.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RPCUtils {

    private static final AsyncResponseMemoryStore asyncResponseMemoryStore = new AsyncResponseMemoryStore();

    // Read the server's response.
    static void readServerResponse(ObjectInputStream in) {
        new Thread(() -> {
            while (true) {
                RPCResponse rpcResponse;
                try {
                    while (true) {
                        rpcResponse = (RPCResponse) in.readObject();
                        asyncResponseMemoryStore.updateAsyncResponse(rpcResponse);
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
            Class[] parameterTypes = new Class[parameters.length + 1];
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Number) {
                    parameterTypes[i] = Number.class;
                } else if (parameters[i] instanceof String) {
                    parameterTypes[i] = String.class;
                } else {
                    parameterTypes[i] = parameters[i].getClass();
                }
            }
            parameterTypes[parameters.length] = UUID.class;
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
        if (parameterTypes == null) {
            return null;
        }

        return new RPCRegistry(
                methodName,
                parameterTypes.length,
                parameterTypes,
                new ArrayList<>(List.of(parameters)),
                RPCResponse.class.getName()
        );
    }

    static AsyncResponse callFunction(ObjectOutputStream out, String methodName, Object... parameters) {
        AsyncResponse asyncResponse = null;
        try {
            RPCRegistry registry = callMethod(methodName, parameters);
            if (registry == null) {
                return new AsyncResponse(
                        new RPCResponse(RPCStatus.FAILED, 0.0, "Method does not exist")
                );
            }
            out.writeObject(registry);
            out.flush();
            asyncResponse = new AsyncResponse();
            asyncResponseMemoryStore.add(registry.getUniqueID(), asyncResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asyncResponse;
    }
}
