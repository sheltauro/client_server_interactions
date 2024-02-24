package org.example.app.utils;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class RPCUtils {

    private static final AsyncResponseMemoryStore asyncResponseMemoryStore = new AsyncResponseMemoryStore();

    // Read the server's response.
    public static void readServerResponse(ObjectInputStream in) {
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

    public static AsyncResponse callFunction(ObjectOutputStream out, String methodName, Object... parameters) {
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

    static void printResponse(String name, RPCResponse response, Object... parameters) {
        System.out.printf("%s(", name);
        for (int i = 0; i < parameters.length; i++) {
            System.out.print(parameters[i]);
            if (i < parameters.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.printf("): %s\n", response.toString());
    }

    public static void readResponseInAnotherThread(String name, AsyncResponse asyncResponse, Object...  parameters) {
        new Thread(() -> {
            try {
                RPCResponse response = asyncResponse.getResponse();
                printResponse(name, response, parameters);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static void sendMessagesToClient(BufferedReader stdin, PrintWriter out) {
        new Thread(() -> {
            String userInput;
            try {
                while ((userInput = stdin.readLine()) != null) {
                    out.println(userInput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void doReflection(RPCRegistry registry, ObjectOutputStream out) {
        RPCResponse response;
        UUID uniqueID = registry.getUniqueID();
        try {
            // Reflection code.
            Class<?> cls = RPCRegistry.class;

            Method method = cls.getDeclaredMethod(registry.getName(), registry.getParameterTypes());

            ArrayList<Object> parameters = registry.getParameters();
            parameters.add(uniqueID);
            response = (RPCResponse) method.invoke(null, parameters.toArray());
            out.writeObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                out.writeObject(new RPCResponse(RPCStatus.FAILED, 0.0, e.getMessage(), uniqueID));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void submitTask(ExecutorService executorService, RPCRegistry registry, ObjectOutputStream out) {
        executorService.submit(() -> doReflection(registry, out));
        System.out.println("Submitted request to thread pool");
    }
}
