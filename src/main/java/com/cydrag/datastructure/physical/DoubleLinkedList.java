package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.exceptions.ConcurrentChangeException;
import com.cydrag.datastructure.exceptions.NullValueException;
import com.cydrag.datastructure.nodes.Node;

public class DoubleLinkedList<T> extends LinkedListBase<T> implements Reversible<T> {

    public DoubleLinkedList() {
        super();
    }

    @Override
    public BidirectionalIterator<T> bidirectionalIterator() {
        return new BidirectionalIterator<>() {

            private Node<T> current = head;
            private Node<T> previous = head;
            private final long modCount = modificationCount;

            @Override
            public boolean hasNext() {
                if (this.modCount != modificationCount) {
                    throw new ConcurrentChangeException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (this.modCount != modificationCount) {
                    throw new ConcurrentChangeException();
                }
                if (this.current == null) {
                    throw new NullValueException();
                }
                this.previous = this.current;

                T value = this.current.getData();
                this.current = this.current.getNext();
                return value;
            }

            @Override
            public boolean hasPrevious() {
                if (this.modCount != modificationCount) {
                    throw new ConcurrentChangeException();
                }
                return this.previous != null;
            }

            @Override
            public T previous() {
                if (this.modCount != modificationCount) {
                    throw new ConcurrentChangeException();
                }
                if (this.previous == null) {
                    throw new NullValueException();
                }
                this.current = this.previous;

                T value = this.previous.getData();
                this.previous = this.previous.getPrevious();
                return value;
            }
        };
    }

    @Override
    protected void addHook(T value, int index) {
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
    protected void removeHook(T value) {

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
    public void clearHook() {

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
