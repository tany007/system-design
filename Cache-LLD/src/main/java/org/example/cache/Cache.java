package org.example.cache;

import org.example.cache.policies.EvictionPolicy;
import org.example.cache.storage.Storage;
import org.example.cache.storage.exception.NotFoundException;
import org.example.cache.storage.exception.StorageFullException;

public class Cache<Key, Value> {
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(Key key, Value value) {
        try{
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception) {
            // Handle the exception
            System.out.println("Storage is full. Cannot add new item.");
            Key evictedKey = evictionPolicy.evictKey();
            if (evictedKey == null) {
                throw new RuntimeException("No key to evict. Storage is full and no eviction policy can be applied.");
            }
            this.storage.remove(evictedKey);
            put(key, value); // Retry adding the new item
        }
    }

    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException exception) {
            // Handle the exception
            System.out.println("Key not found in storage.");
            return null;
        }
    }
}
