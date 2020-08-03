package datastructure.physical;

import datastructure.nodes.Node;

import java.util.Iterator;

// TODO: Think about access modifiers
abstract class LinkedListBase<T> implements LinkedList<T> {

    protected Node<T> head;
    protected Node<T> tail;

    LinkedListBase() {
        this.head = this.tail = null;
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
    public T get(int index) {
        if (this.isEmpty()) {
            throw new NullPointerException("The list is empty.");
        }

        if (index < 0 || index >= this.length()) {
            throw new IndexOutOfBoundsException("Index out of boundaries.");
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
    public boolean contains(T element) {
        if (this.head != null) {
            Node<T> temp = this.head;

            do {
                if (temp.getData().equals(element)) {
                    return true;
                }
                temp = temp.getNext();
            } while ((temp != this.head) && (temp != null));
        }

        return false;
    }

    @Override
    public void remove(int index) {
        T data = this.get(index);
        this.remove(data);
    }

    @Override
    public void addAtStart(T element) {
        this.add(element, 0);
    }

    @Override
    public void addAtEnd(T element) {
        this.add(element, this.length());
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
    public abstract void add(T element, int index);

    @Override
    public abstract void remove(T element);

    @Override
    public abstract void destroy();
}
