package datastructure.physical;

import datastructure.nodes.Node;

import java.util.Iterator;

public abstract class LinkedListBase<T> implements LinkedList<T> {

    protected Node<T> head;
    protected Node<T> tail;

    public LinkedListBase() {
        this.head = this.tail = null;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public int length() {
        if (this.isEmpty()) {
            return 0;
        }

        Node<T> temp = this.head;

        int length = 1;

        while (temp != this.tail) {
            temp = temp.getNext();
            length++;
        }

        return length;
    }

    @Override
    public T get(int index) {
        if (this.isEmpty()) {
            throw new NullPointerException("The list is empty.");
        }

        if (index < 0 || index >= this.length()) {
            throw new IndexOutOfBoundsException("Index out of boundaries.");
        }

        Node<T> current = this.head;
        int count = 0;

        while (count < index) {
            current = current.getNext();
            count++;
        }

        return current.getData();
    }

    @Override
    public boolean contains(T element) {
        if (this.head != null) {
            Node<T> temp = this.head;

            do {
                if (temp.getData().equals(element)) {
                    return true;
                }
                temp = temp.getNext();
            } while ((temp != this.head) && (temp != null));
        }

        return false;
    }

    @Override
    public void remove(int index) {
        T data = this.get(index);
        this.remove(data);
    }

    public void addAtStart(T newData) {
        this.add(newData, 0);
    }

    public void addAtEnd(T newData) {
        this.add(newData, this.length());
    }

    @Override // TODO: Implement this method
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public abstract void add(T value, int location);

    @Override
    public abstract void remove(T value);

    @Override
    public abstract void destroy();
}
