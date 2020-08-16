package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.exceptions.ConcurrentChangeException;
import com.cydrag.datastructure.exceptions.NullValueException;
import com.cydrag.datastructure.nodes.Node;

import java.util.Iterator;

public class CircularDoubleLinkedList<T> extends LinkedListBase<T> implements Reversible<T>, Loopable<T> {

    public CircularDoubleLinkedList() {
        super();
    }

    @Override
    public BidirectionalIterator<T> bidirectionalIterator() {
        return new BidirectionalIterator<>() {

            private Node<T> temp = head;
            final long modCount = modificationCount;

            @Override
            public T previous() {
                if (this.modCount != modificationCount) {
                    throw new ConcurrentChangeException();
                }
                if (this.temp == null) {
                    throw new NullValueException();
                }

                T value = this.temp.getData();
                this.temp = this.temp.getPrevious();
                return value;
            }

            @Override
            public boolean hasPrevious() {
                if (this.modCount != modificationCount) {
                    throw new ConcurrentChangeException();
                }
                return temp != null;
            }

            @Override
            public boolean hasNext() {
                if (this.modCount != modificationCount) {
                    throw new ConcurrentChangeException();
                }
                return temp != null;
            }

            @Override
            public T next() {
                if (this.modCount != modificationCount) {
                    throw new ConcurrentChangeException();
                }
                if (this.temp == null) {
                    throw new NullValueException();
                }

                T value = this.temp.getData();
                this.temp = this.temp.getNext();
                return value;
            }
        };
    }

    @Override
    public Iterator<T> loopIterator() {
        return new LoopIterator<>(this);
    }

    @Override
    protected void addHook(T value, int index) {
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
        else if (index == this.size()) {
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
    protected void removeHook(T value) {
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
    protected void clearHook() {

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
