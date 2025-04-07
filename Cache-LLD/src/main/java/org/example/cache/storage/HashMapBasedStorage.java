package org.example.cache.storage;

import org.example.cache.storage.exception.NotFoundException;
import org.example.cache.storage.exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage <Key, Value> implements Storage<Key, Value> {

    Map<Key, Value> storage;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
    }
    public void add(Key key, Value value) throws StorageFullException {
        storage.put(key, value);
    }

    public void remove(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) {
            throw new NotFoundException();
        }
        storage.remove(key);
    }

    public Value get(Key key) throws NotFoundException {
        return storage.get(key);
    }
}
