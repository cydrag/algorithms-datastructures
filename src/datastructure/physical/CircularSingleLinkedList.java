package datastructure.physical;

import datastructure.nodes.Node;
import datastructure.nodes.SingleNode;

public class CircularSingleLinkedList<T> implements LinkedList<T> {

    private SingleNode<T> head;
    private SingleNode<T> tail;

    public CircularSingleLinkedList() {
        this.head = this.tail = null;
    }

    @Override
    public int size() {

        if (this.head == null) {
            return 0;
        }

        SingleNode<T> temp = this.head;

        int length = 1;

        while (temp != this.tail) {
            temp = temp.getNext();
            length++;
        }

        return length;
    }

    @Override
    public void destroy() {
        if (this.tail != null) {
            this.tail.setNext(null);
        }
        this.head = this.tail = null;
    }

    @Override
    public void remove(T value) {

        if (this.head == null) {
            throw new NullPointerException("The list is empty.");
        }

        SingleNode<T> previous = this.head;

        if (this.head.getData().equals(value)) {
            if (this.head == this.tail) {
                this.tail = this.head = null;

            }
            else {
                this.head = this.head.getNext();
                if (this.head == this.tail) {
                    this.head.setNext(this.head);
                }
                else {
                    this.tail.setNext(this.head);
                }
            }

            previous.setNext(null);
        }
        else {
            while (previous.getNext() != this.head) {

                SingleNode<T> current = previous.getNext();
                if (current.getData().equals(value)) {
                    if (current == this.tail) {
                        this.tail = previous;
                    }
                    previous.setNext(current.getNext());
                    current.setNext(null);
                }
                previous = previous.getNext();
            }
        }
    }

    @Override
    public boolean contains(T value) {

        if (this.head != null) {

            SingleNode<T> temp = this.head;

            do {
                if (temp.getData().equals(value)) {
                    return true;
                }
                temp = temp.getNext();
            } while (temp != head);
        }

        return false;
    }

    @Override
    public void add(T value, int location) {

        if (location < 0 || location > this.size()) {
            throw new IndexOutOfBoundsException("Location not in boundaries.");
        }

        SingleNode<T> newNode = new Node<>(value);

        if (location == 0) {
            if (this.head == null) {
                this.head = this.tail = newNode;
                this.head.setNext(this.head);
            }
            else {
                newNode.setNext(this.head);
                this.head = newNode;
                this.tail.setNext(newNode);
            }
        }
        else if (location == this.size()) {
            newNode.setNext(this.head);
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        else {
            SingleNode<T> root = this.head;
            SingleNode<T> previous = root;
            int count = 0;

            while (count < location) {
                previous = root;
                root = root.getNext();
                count++;
            }

            newNode.setNext(root);
            previous.setNext(newNode);
        }
    }
}
