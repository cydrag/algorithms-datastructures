package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.EmptyDataStructureException;
import com.cydrag.datastructure.exceptions.IllegalCapacityException;
import com.cydrag.datastructure.physical.Array;

abstract class StaticQueue<T> implements Queue<T> {

    final Array<T> array;
    int front, rear;

    StaticQueue(int size) {
        if (size < 1) {
            throw new IllegalCapacityException();
        }

        this.array = new Array<>(size);
        this.front = this.rear = 0;
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
        this.front = this.rear = 0;
    }
}
