package datastructure.physical;

import datastructure.nodes.Node;

public class CircularSingleLinkedList<T> extends LinkedListBase<T> {

    public CircularSingleLinkedList() {
        super();
    }

    public void traverse() {
        if (this.head == null) {
            throw new NullPointerException("The list is empty.");
        }

        Node<T> temp = this.head;

        System.out.print("[ ");

        do {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        } while (temp != this.head);

        System.out.println("]");
    }

    @Override
    public void add(T value, int location) {

        if (location < 0 || location > this.length()) {
            throw new IndexOutOfBoundsException("Location not in boundaries.");
        }

        Node<T> newNode = new Node<>(value);

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

        if (this.head == null) {
            throw new NullPointerException("The list is empty.");
        }

        Node<T> previous = this.head;

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

                Node<T> current = previous.getNext();
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
    public void destroy() {
        if (this.tail != null) {
            this.tail.setNext(null);
        }
        this.head = this.tail = null;
    }
}
