package datastructure.logical;

import datastructure.exceptions.IllegalCapacityException;

public abstract class ArrayQueue<T> implements Queue<T> {

    Object[] values;

    int front, end;

    ArrayQueue(int size) {
        if (size < 1) {
            throw new IllegalCapacityException();
        }

        this.values = new Object[size];
        this.front = this.end = 0;
    }

    @Override
    public abstract void enqueue(T value);

    @Override
    public abstract T dequeue();

    @Override
    public abstract T peek();

    @Override
    public abstract boolean isEmpty();

    public abstract boolean isFull();

    @Override
    public final void clear() {
        this.values = null;
        this.front = this.end = 0;
    }
}
