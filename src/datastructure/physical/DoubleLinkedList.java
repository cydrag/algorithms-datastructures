package datastructure.physical;

import datastructure.nodes.Node;
import datastructure.nodes.SingleNode;

public class DoubleLinkedList<T> implements LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    public DoubleLinkedList() {
        this.head = this.tail = null;
    }

    @Override
    public int size() {

        SingleNode<T> temp = this.head;

        int length = 0;

        while (temp != null) {
            temp = temp.getNext();
            length++;
        }

        return length;
    }

    @Override
    public void destroy() {

        Node<T> temp = this.head;

        while (this.head != this.tail) {
            this.head = this.head.getNext();
            temp.setNext(null);
            this.head.setPrevious(null);
            temp = this.head;
        }

        this.head = this.tail = null;
    }

    @Override
    public void remove(T value) {

        if (this.head == null) {
            throw new NullPointerException("The list is empty.");
        }

        if (this.head.getData().equals(value)) {
            if (this.head.getNext() == null) {
                this.head = this.tail = null;
            }
            else {
                Node<T> prev = this.head;
                this.head = this.head.getNext();
                this.head.setPrevious(null);
                prev.setNext(null);
            }
        }
        else if (this.tail.getData().equals(value)) {
            Node<T> current = this.tail;
            this.tail = this.tail.getPrevious();
            this.tail.setNext(null);
            current.setPrevious(null);
        }
        else {
            Node<T> prev = this.head;
            Node<T> current = prev;

            while (current != null) {
                if (current.getData().equals(value)) {
                    prev.setNext(current.getNext());
                    current.getNext().setPrevious(prev);
                    current.setPrevious(null);
                    current.setNext(null);
                    return;
                }
                prev = current;
                current = current.getNext();
            }
        }
    }

    @Override
    public boolean contains(T value) {

        if (this.head != null) {

            Node<T> root = this.head;

            while (root != null) {
                if (root.getData().equals(value)) {
                    return true;
                }
                root = root.getNext();
            }
        }

        return false;
    }

    public void addAtStart(T value) {
        this.add(value, 0);
    }

    public void addAtEnd(T value) {
        this.add(value, this.size());
    }

    @Override
    public void add(T value, int location) {
        if (location < 0 || location > this.size()) {
            throw new IndexOutOfBoundsException("Location not in boundaries.");
        }
        else if (location == 0) {
            if (this.head == null) {
                this.head = this.tail = new Node<>(value);
            }
            else {
                Node<T> newNode = new Node<>(value);
                newNode.setNext(this.head);
                this.head.setPrevious(newNode);
                this.head = newNode;
            }
        }
        else if (location == this.size()) {
            Node<T> newNode = new Node<>(value);
            newNode.setPrevious(this.tail);
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        else {
            Node<T> current = this.head;
            Node<T> previous = current;

            int count = 0;

            while (count < location) {
                previous = current;
                current = current.getNext();
                count++;
            }

            Node<T> newNode = new Node<>(value);

            newNode.setNext(current);
            newNode.setPrevious(previous);

            current.setPrevious(newNode);
            previous.setNext(newNode);
        }
    }
}
