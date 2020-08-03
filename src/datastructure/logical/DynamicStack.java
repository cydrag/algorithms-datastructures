package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.nodes.Node;

public class DynamicStack<T> implements Stack<T> {

    private Node<T> head;

    public DynamicStack() {
        this.head = null;
    }

    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);

        if (this.head != null) {
            newNode.setNext(this.head);
        }
        this.head = newNode;
    }

    @Override
    public T pop() {

        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }

        T elem = this.head.getData();
        this.head = this.head.getNext();

        return elem;
    }

    @Override
    public T peek() {
        return this.head.getData();
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public void destroy() {
        this.head = null;
    }
}
