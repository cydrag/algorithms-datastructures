package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.exceptions.FullDataStructureException;

public class FixedQueue<T> extends ArrayQueue<T> {

    public FixedQueue(int size) {
        super(size);
    }

    @Override
    public void enqueue(T element) {
        if (this.isFull()) {
            throw new FullDataStructureException();
        }
        this.values[this.end++] = element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }

        Object obj = this.values[this.front++];
        if (this.isEmpty()) {
            this.front = this.end = 0;
        }

        return (T)obj;
    }

    @Override
    public boolean isEmpty() {
        return this.front == this.end;
    }

    public boolean isFull() {
        return this.end == this.values.length;
    }
}
