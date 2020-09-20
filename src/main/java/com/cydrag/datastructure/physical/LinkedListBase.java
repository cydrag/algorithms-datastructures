package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.exceptions.ConcurrentChangeException;
import com.cydrag.datastructure.exceptions.EmptyDataStructureException;
import com.cydrag.datastructure.exceptions.IndexNotInBoundsException;
import com.cydrag.datastructure.exceptions.NullValueException;
import com.cydrag.datastructure.nodes.Node;

import java.util.Iterator;
import java.util.Objects;

abstract class LinkedListBase<T> implements LinkedList<T> {

    Node<T> head;
    Node<T> tail;
    long modificationCount;

    LinkedListBase() {
        this.head = this.tail = null;
        this.modificationCount = Long.MIN_VALUE;
    }

    private void checkEmpty() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
    }

    private void checkAddBounds(int index) {
        if ((index < 0) || (index > this.size())) {
            throw new IndexNotInBoundsException(index, this.size());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            Node<T> temp = head;
            boolean wasTail = false;
            final long modCount = modificationCount;

            @Override
            public boolean hasNext() {
                if (this.modCount != modificationCount) {
                    throw new ConcurrentChangeException();
                }
                return (this.temp != null) && (!this.wasTail);
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

                if (this.temp == tail) {
                    this.wasTail = true;
                }

                this.temp = this.temp.getNext();
                return value;
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
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
    public void add(T value) {
        this.addAtEnd(value);
    }

    @Override
    public T get(int index) {
        this.checkEmpty();

        if (index < 0 || index >= this.size()) {
            throw new IndexNotInBoundsException(index, this.size());
        }

        Node<T> current = this.head;
        int count = 0;

        while (count < index) {
            current = current.getNext();
            count++;
        }

        return current.getData();
    }

    @Override
    public boolean contains(T value) {

        if (this.isEmpty()) {
            return false;
        }

        Node<T> temp = this.head;

        do {
            T currentElem = temp.getData();

            if (Objects.equals(currentElem, value)) {
                return true;
            }

            temp = temp.getNext();
        } while ((temp != this.head) && (temp != null));

        return false;
    }

    @Override
    public void remove(int index) {
        T data = this.get(index);
        this.remove(data);
    }

    @Override
    public void addAtStart(T value) {
        this.add(value, 0);
    }

    @Override
    public void addAtEnd(T value) {
        this.add(value, this.size());
    }

    @Override
    public void removeAtStart() {
        this.remove(0);
    }

    @Override
    public void removeAtEnd() {
        this.remove(this.size() - 1);
    }

    @Override
    public T getAtStart() {
        this.checkEmpty();
        return this.head.getData();
    }

    @Override
    public T getAtEnd() {
        this.checkEmpty();
        return this.tail.getData();
    }

    public void add(T value, int index) {
        this.checkAddBounds(index);
        this.addHook(value, index);
        this.modificationCount++;
    }

    public void remove(T value) {
        this.checkEmpty();
        this.removeHook(value);
        this.modificationCount++;
    }

    public void clear() {
        this.clearHook();
        this.modificationCount++;
    }

    protected abstract void addHook(T value, int index);
    protected abstract void removeHook(T value);
    protected abstract void clearHook();
}
