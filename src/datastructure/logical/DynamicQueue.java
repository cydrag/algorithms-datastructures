package datastructure.logical;

import datastructure.physical.LinkedList;
import datastructure.physical.SingleLinkedList;

public class DynamicQueue<T> implements Queue<T> {

    private final LinkedList<T> linkedList;

    public DynamicQueue() {
        this.linkedList = new SingleLinkedList<>();
    }

    @Override
    public void enqueue(T element) {
        this.linkedList.addAtEnd(element);
    }

    @Override
    public T dequeue() {
        T elem = this.linkedList.getAtStart();
        this.linkedList.removeAtStart();
        return elem;
    }

    @Override
    public T peek() {
        return this.linkedList.getAtStart();
    }

    @Override
    public boolean contains(T element) {
        return this.linkedList.contains(element);
    }

    @Override
    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    @Override
    public void clear() {
        this.linkedList.clear();
    }
}
