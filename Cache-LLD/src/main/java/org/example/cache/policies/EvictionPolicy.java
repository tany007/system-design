package org.example.cache.policies;

/**
 * This interface defines the basic operations for an eviction policy.
 * @param <Key>
 */
public interface EvictionPolicy<Key> {
    void keyAccessed(Key key);

    /**
     * This method is called when a key needs to be evicted from the cache.
     * @return The key that was evicted.
     */
    Key evictKey();
}
