package datastructure.physical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.exceptions.IndexNotInBoundsException;
import datastructure.nodes.Node;

import java.util.Iterator;

abstract class LinkedListBase<T> implements LinkedList<T> {

    Node<T> head;
    Node<T> tail;

    LinkedListBase() {
        this.head = this.tail = null;
    }

    void checkEmpty() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
    }

    void checkAddBounds(int index) {
        if ((index < 0) || (index > this.length())) {
            throw new IndexNotInBoundsException(index, this.length());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            Node<T> temp = head;
            boolean wasTail = false;

            @Override
            public boolean hasNext() {
                try {
                    return !wasTail;
                } finally {
                    if (temp == tail) {
                        wasTail = true;
                    }
                }
            }

            @Override
            public T next() {
                try {
                    return temp.getData();
                } finally {
                    temp = temp.getNext();
                }
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public int length() {
        if (this.isEmpty()) {
            return 0;
        }

        Node<T> temp = this.head;

        int length = 1;

        while (temp != this.tail) {
            temp = temp.getNext();
            length++;
        }

        return length;
    }

    @Override
    public void add(T value) {
        this.addAtEnd(value);
    }

    @Override
    public T get(int index) {
        this.checkEmpty();

        if (index < 0 || index >= this.length()) {
            throw new IndexNotInBoundsException(index, this.length());
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
        this.add(value, this.length());
    }

    @Override
    public void removeAtStart() {
        this.remove(0);
    }

    @Override
    public void removeAtEnd() {
        this.remove(this.length() - 1);
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

    @Override
    public abstract void add(T value, int index);

    @Override
    public abstract void remove(T value);

    @Override
    public abstract void clear();
}
