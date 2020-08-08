package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.EmptyDataStructureException;
import com.cydrag.datastructure.exceptions.FullDataStructureException;
import com.cydrag.datastructure.exceptions.IllegalCapacityException;
import com.cydrag.datastructure.physical.Array;

public class FixedStack<T> implements Stack<T> {

    private Array<T> array;

    private int i;
    private int lastIndex;

    public FixedStack(int size) {
        if (size < 1) {
            throw new IllegalCapacityException();
        }

        this.array = new Array<>(size);
        this.i = -1;
        this.lastIndex = size - 1;
    }

    @Override
    public void push(T value) {
        if (this.isFull()) {
            throw new FullDataStructureException();
        }
        else {
            this.array.add(value, ++this.i);
        }
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
        else {
            return this.array.get(this.i--);
        }
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
        return this.array.get(this.i);
    }

    @Override
    public boolean contains(T value) {
        return this.array.contains(value);
    }

    @Override
    public boolean isEmpty() {
        return this.i == -1;
    }

    public boolean isFull() {
        return this.i == this.lastIndex;
    }

    @Override
    public void clear() {
        this.array.clear();
        this.i = -1;
        this.lastIndex = this.array.length() - 1;
    }
}
