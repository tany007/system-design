package org.example.cache.storage;

import org.example.cache.storage.exception.NotFoundException;
import org.example.cache.storage.exception.StorageFullException;

/**
 * This interface defines the basic operations for a storage system.
 * @param <Key>
 * @param <Value>
 */
public interface Storage<Key, Value> {
    void add(Key key, Value value) throws StorageFullException;

    void remove(Key key) throws NotFoundException;

    Value get(Key key) throws NotFoundException;
}
