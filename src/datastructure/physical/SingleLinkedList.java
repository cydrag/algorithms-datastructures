package datastructure.physical;

import datastructure.nodes.Node;
import datastructure.nodes.SingleNode;

public class SingleLinkedList<T> implements LinkedList<T> {

    private SingleNode<T> head;
    private SingleNode<T> tail;

    public SingleLinkedList() {
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

    public void traverse() {
        if (this.head == null) {
            throw new NullPointerException("The list is empty.");
        }
        else {
            SingleNode<T> root = this.head;

            System.out.print("[ ");
            while (root != null) {
                System.out.print(root.getData() + " ");
                root = root.getNext();
            }
            System.out.println("]");
        }
    }

    @Override
    public void destroy() {
        this.head = this.tail = null;
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

        SingleNode<T> previous = this.head;
        SingleNode<T> current = this.head;

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
    public boolean contains(T value) {

        if (this.head != null) {
            SingleNode<T> temp = head;

            while (temp != null) {
                if (temp.getData().equals(value)) {
                    return true;
                }
                temp = temp.getNext();
            }
        }

        return false;
    }

    public void addAtEnd(T newData) {
        this.add(newData, this.size());
    }

    public void addAtStart(T newData) {
        this.add(newData, 0);
    }

    @Override
    public void add(T newData, int location) {
        if (location < 0 || location > this.size()) {
            throw new IndexOutOfBoundsException("Index out of boundaries.");
        }
        else if (location == 0) {
            if (this.head == null) {
                    this.head = this.tail = new Node<>(newData);
            }
            else {
                SingleNode<T> newNode = new Node<>(newData);
                newNode.setNext(this.head);
                this.head = newNode;
            }
        }
        else if (location == this.size()) {
            SingleNode<T> newNode = new Node<>(newData);
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        else {
            SingleNode<T> current = this.head;
            SingleNode<T> previous = current;
            int count = 0;

            while (count < location) {
                previous = current;
                current = current.getNext();
                count++;
            }

            SingleNode<T> newNode = new Node<>(newData);

            newNode.setNext(current);
            previous.setNext(newNode);
        }
    }
}
