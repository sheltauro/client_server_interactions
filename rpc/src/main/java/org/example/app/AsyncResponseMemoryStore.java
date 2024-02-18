package org.example.app;

import java.util.HashMap;
import java.util.UUID;

public class AsyncResponseMemoryStore {
    private final HashMap<UUID, AsyncResponse> store;

    public AsyncResponseMemoryStore() {
        store = new HashMap<>();
    }

    public synchronized void add(UUID uuid, AsyncResponse response) {
        store.put(uuid, response);
    }

    public synchronized AsyncResponse get(UUID uuid) {
        return store.get(uuid);
    }

    public synchronized void remove(UUID uuid) {
        store.remove(uuid);
    }

    public synchronized void updateAsyncResponse(RPCResponse response) {
        store.get(response.getUuid()).setResponse(response);
    }
}
