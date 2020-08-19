package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.EmptyDataStructureException;
import com.cydrag.datastructure.exceptions.FullDataStructureException;

public class ArrayQueue<T> extends StaticQueue<T> {

    public ArrayQueue(int size) {
        super(size);
    }

    @Override
    public void enqueue(T value) {
        if (this.isFull()) {
            throw new FullDataStructureException();
        }
        this.array.add(value, this.rear++);
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }

        T obj = this.array.get(this.front++);
        if (this.isEmpty()) {
            this.front = this.rear = 0;
        }

        return obj;
    }

    @Override
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public boolean isFull() {
        return this.rear == this.array.size();
    }
}
