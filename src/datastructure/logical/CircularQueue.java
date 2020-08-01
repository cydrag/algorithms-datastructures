package datastructure.logical;

import datastructure.exceptions.StatusException;

public class CircularQueue<T> extends ArrayQueue<T> {

    private int currentSize;
    private final int MAX_SIZE;

    public CircularQueue(int size) {
        super(size);
        this.currentSize = 0;
        this.MAX_SIZE = size;
    }

    @Override
    public void enqueue(T value) {
        if (this.isFull()) {
            throw new StatusException("Cannot add element to a queue. The queue is full.");
        }

        this.currentSize++;
        super.values[super.end] = value;
        super.end = (super.end + 1) % this.MAX_SIZE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new StatusException("Cannot remove element from the queue. The queue is empty.");
        }

        this.currentSize--;
        Object obj = super.values[super.front];
        super.front = (super.front + 1) % this.MAX_SIZE;
        return (T)obj;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new StatusException("Cannot peek to a queue. The queue is empty.");
        }
        return (T)super.values[super.front];
    }

    @Override
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    public boolean isFull() {
        return this.currentSize == this.MAX_SIZE;
    }
}
