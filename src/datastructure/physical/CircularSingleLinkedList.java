package datastructure.physical;

import datastructure.nodes.Node;

public class CircularSingleLinkedList<T> extends LinkedListBase<T> {

    public CircularSingleLinkedList() {
        super();
    }

    @Override
    public void add(T value, int location) {

        if (location < 0 || location > this.length()) {
            throw new IndexOutOfBoundsException("Index not in boundaries.");
        }

        Node<T> newNode = new Node<>(value);

        if (this.head == null) {
            this.head = this.tail = newNode;
            this.head.setNext(this.head);
        }
        else if (location == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
            this.tail.setNext(newNode);
        }
        else if (location == this.length()) {
            newNode.setNext(this.head);
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        else {
            Node<T> root = this.head;
            Node<T> previous = root;
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

    @Override
    public void remove(T value) {

        if (this.isEmpty()) {
            throw new NullPointerException("The list is empty.");
        }

        Node<T> previous = this.head;

        if (this.head.getData().equals(value)) {
            if (this.head == this.tail) {
                this.tail = this.head = null;
            }
            else {
                this.head = this.head.getNext();
                this.tail.setNext(this.head);
            }

            previous.setNext(null);
        }
        else {
            while (previous.getNext() != this.head) {
                Node<T> current = previous.getNext();

                if (current.getData().equals(value)) {
                    if (current == this.tail) {
                        this.tail = previous;
                    }
                    previous.setNext(current.getNext());
                    current.setNext(null);
                    break;
                }
                previous = previous.getNext();
            }
        }
    }

    @Override
    public void destroy() {
        if (this.tail != null) {
            this.tail.setNext(null);
        }
        this.head = this.tail = null;
    }
}
