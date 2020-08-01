package datastructure.logical;

import datastructure.exceptions.StatusException;

public class FixedQueue<T> extends ArrayQueue<T> {

    public FixedQueue(int size) {
        super(size);
    }

    @Override
    public void enqueue(T value) {
        if (this.isFull()) {
            throw new StatusException("Cannot add element to a queue. The queue is full.");
        }
        super.values[super.end++] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new StatusException("Cannot remove element from the queue. The queue is empty.");
        }

        Object obj = super.values[super.front++];
        if (this.isEmpty()) {
            super.front = super.end = 0;
        }

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
        return super.front == super.end;
    }

    public boolean isFull() {
        return super.end == super.values.length;
    }
}
