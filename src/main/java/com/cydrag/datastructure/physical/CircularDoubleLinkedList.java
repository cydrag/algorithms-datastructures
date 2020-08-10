package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.nodes.Node;

import java.util.Iterator;

public class CircularDoubleLinkedList<T> extends LinkedListBase<T> implements Reversible<T> {

    public CircularDoubleLinkedList() {
        super();
    }

    @Override
    public Iterator<T> reverseIterator() {
        return new Iterator<>() {

            Node<T> temp = tail;
            boolean wasHead = false;

            @Override
            public boolean hasNext() {
                return (temp != null) && (!wasHead);
            }

            @Override
            public T next() {
                T value = temp.getData();
                if (temp == CircularDoubleLinkedList.super.head) {
                    wasHead = true;
                }
                temp = temp.getPrevious();
                return value;
            }
        };
    }

    @Override
    public void add(T value, int index) {
        this.checkAddBounds(index);

        Node<T> newNode = new Node<>(value);

        if (this.head == null) {
            this.head = this.tail = newNode;
            this.head.setNext(this.head);
            this.head.setPrevious(this.head);
        }
        else if (index == 0) {
            newNode.setNext(this.head);
            newNode.setPrevious(this.tail);

            this.tail.setNext(newNode);
            this.head.setPrevious(newNode);

            this.head = newNode;
        }
        else if (index == this.length()) {
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

            while (count < index) {
                previous = current;
                current = current.getNext();
                count++;
            }

            newNode.setNext(current);
            newNode.setPrevious(previous);

            previous.setNext(newNode);
            current.setPrevious(newNode);
        }
    }

    @Override
    public void remove(T value) {
        this.checkEmpty();

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
    public void clear() {

        if (this.head != null) {

            this.head.setPrevious(null);
            this.tail.setNext(null);

            Node<T> prev = this.head;

            while (this.head != this.tail) {
                this.head = this.head.getNext();
                prev.setNext(null);
                this.head.setPrevious(null);
                prev = this.head;
            }
        }

        this.head = this.tail = null;
    }
}
