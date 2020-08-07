package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.exceptions.FullDataStructureException;

public class FixedQueue<T> extends ArrayQueue<T> {

    public FixedQueue(int size) {
        super(size);
    }

    @Override
    public void enqueue(T value) {
        if (this.isFull()) {
            throw new FullDataStructureException();
        }
        this.array.add(value, this.end++);
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }

        T obj = this.array.get(this.front++);
        if (this.isEmpty()) {
            this.front = this.end = 0;
        }

        return obj;
    }

    @Override
    public boolean isEmpty() {
        return this.front == this.end;
    }

    public boolean isFull() {
        return this.end == this.array.length();
    }
}
