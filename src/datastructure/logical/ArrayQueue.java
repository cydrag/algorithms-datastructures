package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
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
    public abstract void enqueue(T element);

    @Override
    public abstract T dequeue();

    @Override
    public abstract boolean isEmpty();

    public abstract boolean isFull();

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
        return (T)this.values[this.front];
    }

    @Override
    public final void destroy() {
        this.values = null;
        this.front = this.end = 0;
    }
}
