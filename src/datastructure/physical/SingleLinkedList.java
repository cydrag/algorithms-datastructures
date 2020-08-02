package datastructure.physical;

import datastructure.nodes.Node;

public class SingleLinkedList<T> extends LinkedListBase<T> {

    public SingleLinkedList() {
        super();
    }

    public void traverse() {

        Node<T> root = this.head;

        System.out.print("[ ");
        while (root != null) {
            System.out.print(root.getData() + " ");
            root = root.getNext();
        }
        System.out.println("]");

    }

    @Override
    public void add(T newData, int location) {
        if (location < 0 || location > this.length()) {
            throw new IndexOutOfBoundsException("Index out of boundaries.");
        }

        Node<T> newNode = new Node<>(newData);

        if (this.head == null) {
            this.head = this.tail = newNode;
        }
        else if (location == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
        }
        else if (location == this.length()) {
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

            newNode.setNext(current);
            previous.setNext(newNode);
        }
    }

    @Override
    public void remove(T value) {

        if (this.head == null) {
            throw new NullPointerException("The list is empty.");
        }

        if (this.head.getData().equals(value)) {
            if (this.head.getNext() == null) {
                this.tail = this.head = null;
            }
            else {
                this.head = this.head.getNext();
            }
            return;
        }

        Node<T> previous = this.head;
        Node<T> current = this.head;

        while (current != null) {
            if (current.getData().equals(value)) {

                if (current == this.tail) {
                    this.tail = previous;
                }

                previous.setNext(current.getNext());
                return;
            }
            previous = current;
            current = current.getNext();
        }
    }

    @Override
    public void destroy() {
        this.head = this.tail = null;
    }
}
