package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.EmptyDataStructureException;
import com.cydrag.datastructure.exceptions.IllegalCapacityException;
import com.cydrag.datastructure.physical.Array;

abstract class ArrayQueue<T> implements Queue<T> {

    final Array<T> array;
    int front, end;

    ArrayQueue(int size) {
        if (size < 1) {
            throw new IllegalCapacityException();
        }

        this.array = new Array<>(size);
        this.front = this.end = 0;
    }

    public abstract boolean isFull();

    @Override
    public boolean contains(T value) {
        return this.array.contains(value);
    }

    @Override
    public int size() {
        return this.array.size();
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
        return this.array.get(this.front);
    }

    @Override
    public void clear() {
        this.array.clear();
        this.front = this.end = 0;
    }
}
