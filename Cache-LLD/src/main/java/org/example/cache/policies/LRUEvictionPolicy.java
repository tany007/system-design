package org.example.cache.policies;

import org.example.algorithms.DoublyLinkedList;
import org.example.algorithms.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the LRU (Least Recently Used) algorithm.
 * It keeps track of the order in which keys are accessed and evicts the least recently used key when needed.
 * @param <Key>
 */
public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final DoublyLinkedList<Key> dll;
    private final Map<Key, DoublyLinkedListNode<Key>> map;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.map = new HashMap<>();
    }
    public void keyAccessed(Key key) {
        if(map.containsKey(key)) {
            dll.detachNode(map.get(key));
            dll.addNodeAtLast(map.get(key));
        } else {
            DoublyLinkedListNode<Key> newNode = dll.addElementAtLast(key);
            map.put(key, newNode);
        }

    }

    public Key evictKey() {
        DoublyLinkedListNode<Key> first = dll.getFirstNode();
        if(first == null)
            return null;
        dll.detachNode(first);
        return first.getElement();
    }
}
