package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.nodes.Node;

import java.util.Iterator;

public class DoubleLinkedList<T> extends LinkedListBase<T> implements Reversible<T> {

    public DoubleLinkedList() {
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
                if (temp == DoubleLinkedList.super.head) {
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
        }
        else if (index == 0) {
            newNode.setNext(this.head);
            this.head.setPrevious(newNode);
            this.head = newNode;
        }
        else if (index == this.size()) {
            newNode.setPrevious(this.tail);
            this.tail.setNext(newNode);
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

            current.setPrevious(newNode);
            previous.setNext(newNode);
        }
    }

    @Override
    public void remove(T value) {
        this.checkEmpty();

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
                    break;
                }
                prev = current;
                current = current.getNext();
            }
        }
    }

    @Override
    public void clear() {

        Node<T> prev = this.head;

        while (this.head != this.tail) {
            this.head = this.head.getNext();
            prev.setNext(null);
            this.head.setPrevious(null);
            prev = this.head;
        }

        this.head = this.tail = null;
    }
}
