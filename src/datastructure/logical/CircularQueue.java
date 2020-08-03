package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.exceptions.FullDataStructureException;

public class CircularQueue<T> extends ArrayQueue<T> {

    private int currentSize;
    private final int MAX_SIZE;

    public CircularQueue(int size) {
        super(size);
        this.currentSize = 0;
        this.MAX_SIZE = size;
    }

    @Override
    public void enqueue(T element) {
        if (this.isFull()) {
            throw new FullDataStructureException();
        }

        this.currentSize++;
        this.values[this.end] = element;
        this.end = (this.end + 1) % this.MAX_SIZE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }

        this.currentSize--;
        Object obj = this.values[this.front];
        this.front = (this.front + 1) % this.MAX_SIZE;
        return (T)obj;
    }

    @Override
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    @Override
    public boolean isFull() {
        return this.currentSize == this.MAX_SIZE;
    }
}
