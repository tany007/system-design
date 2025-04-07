package org.example.algorithms;

import org.example.algorithms.exceptions.InvalidNodeException;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {

    DoublyLinkedListNode<E> head;
    DoublyLinkedListNode<E> tail;

    public DoublyLinkedList() {
        head = new DoublyLinkedListNode<>(null);
        tail = new DoublyLinkedListNode<>(null);

        head.next = tail;
        tail.prev = head;
    }

    /**
     * Method to detach a random node from the doubly linked list. The node itself will not be removed from the memory.
     * Just the references will be changed.
     *
     * @param node Node to be detached
     */
    public void detachNode(DoublyLinkedListNode<E> node) {
        if (node == null) {
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    /**
     * Helper Method to add a node at the end of the doubly linked list
     *
     * @param node Node to be added
     */
    public void addNodeAtLast(DoublyLinkedListNode<E> node) {
        if(node == null) {
            throw new InvalidNodeException();
        }
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    public DoublyLinkedListNode<E> addElementAtLast(E element) {
        if(element == null) {
            throw new InvalidNodeException();
        }
        DoublyLinkedListNode<E> newNode = new DoublyLinkedListNode<>(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    public boolean isItemPresent() {
        return head.next != tail;
    }

    public DoublyLinkedListNode<E> getFirstNode() throws NoSuchElementException{
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return head.next;
    }

    public DoublyLinkedListNode getLastNode() throws NoSuchElementException {
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return tail.prev;
    }


}
