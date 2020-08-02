package datastructure.logical;

import datastructure.exceptions.StatusException;
import datastructure.nodes.Node;

public class DynamicQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;

    public DynamicQueue() {
        this.head = this.tail = null;
    }

    @Override
    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);

        if (this.tail == null) {
            this.head = this.tail = newNode;
        }
        else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new StatusException("Cannot remove element from the queue. The queue is empty.");
        }

        T obj = this.head.getData();
        this.head = this.head.getNext();
        if (this.head == null) {
            this.tail = null;
        }
        return obj;
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new StatusException("Cannot peek to a queue. The queue is empty.");
        }
        return this.head.getData();
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public void clear() {
        this.head = this.tail = null;
    }
}
