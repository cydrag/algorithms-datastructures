package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.nodes.Node;

public class PriorityQueue<T extends Comparable<? super T>> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;

    public PriorityQueue() {
        this.head = this.tail = null;
    }

    private void checkEmpty() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
    }

    @Override
    public void enqueue(T element) {

        Node<T> newNode = new Node<>(element);

        if (this.isEmpty()) {
            this.head = this.tail = newNode;
        }
        else if (this.head.getData() == null) {
            newNode.setNext(this.head);
            this.head = newNode;
        }
        else if (element == null) {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        else {
            Node<T> current = this.head;
            Node<T> previous = current;

            while (current != null) {

                T currentData = current.getData();

                if (element.compareTo(currentData) < 0) {
                    if (current == this.head) {
                        newNode.setNext(this.head);
                        this.head = newNode;
                    }
                    else {
                        newNode.setNext(current);
                        previous.setNext(newNode);
                    }
                    break;
                }

                previous = current;
                current = current.getNext();
            }

            if (current == null) {
                previous.setNext(newNode);
                this.tail = newNode;
            }
        }
    }

    @Override
    public T dequeue() {
        this.checkEmpty();

        Node<T> temp = this.head;
        this.head = this.head.getNext();
        temp.setNext(null);

        return temp.getData();
    }

    @Override
    public T peek() {
        this.checkEmpty();
        return this.head.getData();
    }

    @Override
    public boolean contains(T element) {
        if (this.isEmpty()) {
            return false;
        }

        Node<T> temp = this.head;

        do {
            T currentElem = temp.getData();

            if (currentElem == element) {
                return true;
            }
            else if (element != null) {
                if (element.equals(currentElem)) {
                    return true;
                }
            }

            temp = temp.getNext();
        } while ((temp != this.head) && (temp != null));

        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public void clear() {
        this.head = this.tail = null;
    }
}
