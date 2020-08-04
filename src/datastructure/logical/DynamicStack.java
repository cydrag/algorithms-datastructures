package datastructure.logical;

import datastructure.physical.LinkedList;
import datastructure.physical.SingleLinkedList;

public class DynamicStack<T> implements Stack<T> {

    private final LinkedList<T> linkedList;

    public DynamicStack() {
        this.linkedList = new SingleLinkedList<>();
    }

    @Override
    public void push(T element) {
        this.linkedList.addAtStart(element);
    }

    @Override
    public T pop() {
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
