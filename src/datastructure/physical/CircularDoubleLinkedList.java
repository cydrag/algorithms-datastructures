package datastructure.physical;

import datastructure.nodes.Node;
import datastructure.nodes.SingleNode;

public class CircularDoubleLinkedList<T> implements LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    public CircularDoubleLinkedList() {
        this.head = this.tail = null;
    }

    @Override
    public int size() {

        if (this.head == null) {
            return 0;
        }

        SingleNode<T> temp = head;

        int count = 0;

        do {
            temp = temp.getNext();
            count++;
        } while (temp != this.head);

        return count;
    }

    @Override
    public void destroy() {

        if (this.head != null) {
            do {
                this.head = this.head.getNext();
                this.head.getPrevious().setNext(null);
                this.head.getPrevious().setPrevious(null);
                this.head.setPrevious(null);
            } while (this.head != this.tail);

            this.tail.setNext(null);

            this.head = this.tail = null;
        }
    }

    @Override
    public void remove(T value) {

        if (this.head == null) {
            throw new NullPointerException("The list is empty.");
        }

        Node<T> previous = this.head;
        if (this.head.getData().equals(value)) {

            this.head = this.head.getNext();
            this.head.setPrevious(this.tail);
            this.tail.setNext(this.head);

            previous.setNext(null);
            previous.setPrevious(null);

            if (this.head == previous) {
                this.head = this.tail = null;
            }
        }
        else {
            Node<T> current = this.head.getNext();

            while (current != head) {
                if (current.getData().equals(value)) {
                    previous.setNext(current.getNext());
                    current.setPrevious(null);
                    current.getNext().setPrevious(previous);
                    current.setNext(null);

                    if (this.tail == current) {
                        this.tail = previous;
                    }

                    break;
                }
                previous = current;
                current = current.getNext();
            }
        }
    }

    @Override
    public boolean contains(T value) {

        if (this.head == null) {
            throw new NullPointerException("The list is empty.");
        }

        SingleNode<T> root = this.head;

        do {
            if (root.getData().equals(value)) {
                return true;
            }
            root = root.getNext();
        } while (root != this.head);

        return false;
    }

    @Override
    public void add(T value, int location) {
        if (location < 0 || location > this.size()) {
            throw new IndexOutOfBoundsException("Location not in boundaries.");
        }
        else if (location == 0) {
            if (this.head == null) {
                this.head = this.tail = new Node<>(value);
                this.head.setNext(this.head);
                this.head.setPrevious(this.head);
            }
            else {
                Node<T> newNode = new Node<>(value);

                newNode.setNext(this.head);
                newNode.setPrevious(this.tail);

                this.tail.setNext(newNode);
                this.head.setPrevious(newNode);

                this.head = newNode;
            }
        }
        else if (location == this.size()) {
            Node<T> newNode = new Node<>(value);

            newNode.setNext(this.head);
            newNode.setPrevious(this.tail);

            this.tail.setNext(newNode);
            this.head.setPrevious(newNode);

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

            previous.setNext(newNode);
            current.setPrevious(newNode);
        }
    }
}
