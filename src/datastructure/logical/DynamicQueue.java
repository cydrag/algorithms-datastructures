package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.nodes.Node;

public class DynamicQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;

    public DynamicQueue() {
        this.head = this.tail = null;
    }

    @Override
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);

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
            throw new EmptyDataStructureException();
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
            throw new EmptyDataStructureException();
        }
        return this.head.getData();
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public void destroy() {
        this.head = this.tail = null;
    }
}
