package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.EmptyDataStructureException;
import com.cydrag.datastructure.nodes.Node;

import java.util.Comparator;

// TODO: Code reduction, expose all structures on one package only
public class PriorityQueue<T extends Comparable<? super T>> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private final Comparator<T> comparator;

    public PriorityQueue() {
        this.head = this.tail = null;
        this.comparator = Comparator.naturalOrder();
    }

    public PriorityQueue(Comparator<T> comparator) {
        this.head = this.tail = null;
        this.comparator = comparator;
    }

    private void checkEmpty() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
    }

    @Override
    public void enqueue(T value) {

        Node<T> newNode = new Node<>(value);

        if (this.isEmpty()) {
            this.head = this.tail = newNode;
        }
        else if (value == null) {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        else if (this.head.getData() == null) {
            newNode.setNext(this.head);
            this.head = newNode;
        }
        else {
            Node<T> current = this.head;
            Node<T> previous = current;

            while (current != null) {

                T currentData = current.getData();

                if (currentData == null) {
                    newNode.setNext(current);
                    previous.setNext(newNode);
                    break;
                }
                else if (comparator.compare(value, currentData) > 0) {
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
                if (previous != null) {
                    previous.setNext(newNode);
                }
                this.tail = newNode;
            }
        }
    }

    @Override
    public void remove(T value) {
        this.checkEmpty();

        if (this.head.getData().equals(value)) {
            if (this.head.getNext() == null) {
                this.tail = this.head = null;
            }
            else {
                Node<T> previous = this.head;
                this.head = this.head.getNext();
                previous.setNext(null);
            }
        }
        else {
            Node<T> previous = this.head;
            Node<T> current = this.head;

            while (current != null) {
                if (current.getData().equals(value)) {

                    if (current == this.tail) {
                        this.tail = previous;
                    }

                    previous.setNext(current.getNext());
                    current.setNext(null);
                    break;
                }
                previous = current;
                current = current.getNext();
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
    public boolean contains(T value) {
        if (this.isEmpty()) {
            return false;
        }

        Node<T> temp = this.head;

        do {
            T currentElem = temp.getData();

            if (currentElem == value) {
                return true;
            }
            else if (value != null) {
                if (value.equals(currentElem)) {
                    return true;
                }
            }

            temp = temp.getNext();
        } while ((temp != this.head) && (temp != null));

        return false;
    }

    @Override
    public int size() {
        if (this.isEmpty()) {
            return 0;
        }

        Node<T> temp = this.head;

        int size = 1;

        while (temp != this.tail) {
            temp = temp.getNext();
            size++;
        }

        return size;
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
