package org.example.app;

import org.example.app.utils.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

    // Read user input and send it to the server.
    static void userInput(BufferedReader stdin, ObjectOutputStream out) {
        try {
            String userInput;
            while ((userInput = stdin.readLine()) != null) {
                String[] strings = userInput.split(" ");
                Double[] numbers = new Double[strings.length - 1];
                for (int i = 1; i < strings.length; i++) {
                    numbers[i - 1] = Double.parseDouble(strings[i]);
                }
                AsyncResponse asyncResponse = RPCUtils.callFunction(out, strings[0], (Object[]) numbers);
                RPCUtils.readResponseInAnotherThread(strings[0],asyncResponse, (Object[]) numbers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test the RPCRegistry class.
    static void testRPCRegistry(ObjectInputStream in, ObjectOutputStream out) {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(2.0);
        RPCRegistry rpcRegistry = new RPCRegistry(
                "doubled",
                1,
                new Class[]{Number.class},
                parameters,
                RPCResponse.class.getName()
        );

        try {
            out.writeObject(rpcRegistry);
            out.flush();
            RPCResponse response = (RPCResponse) in.readObject();
            assert (response.toString().equals(
                    new RPCResponse(RPCStatus.SUCCEEDED, 4.0).toString()));
            System.out.println("Test 1 passed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 8080);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String mode = "interactive";

            // Switch between interactive and test mode.
            switch (mode) {
                case "interactive" -> {
                    RPCUtils.readServerResponse(in);
                    userInput(stdin, out);
                }
                case "test" -> testRPCRegistry(in, out);
                default -> throw new IllegalArgumentException("Invalid mode: " + mode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
