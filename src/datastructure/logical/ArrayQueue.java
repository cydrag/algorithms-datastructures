package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.exceptions.IllegalCapacityException;
import datastructure.physical.Array;

abstract class ArrayQueue<T> implements Queue<T> {

    Array<T> array;
    int front, end;

    ArrayQueue(int size) {
        if (size < 1) {
            throw new IllegalCapacityException();
        }

        this.array = new Array<>(size);
        this.front = this.end = 0;
    }

    @Override
    public abstract void enqueue(T element);

    @Override
    public abstract T dequeue();

    @Override
    public abstract boolean isEmpty();

    public abstract boolean isFull();

    @Override
    public boolean contains(T element) {
        return array.contains(element);
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
        return array.get(this.front);
    }

    @Override
    public final void clear() {
        array.clear();
        this.front = this.end = 0;
    }
}
