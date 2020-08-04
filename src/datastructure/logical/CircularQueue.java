package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.exceptions.FullDataStructureException;

public class CircularQueue<T> extends ArrayQueue<T> {

    private int numberOfElements;
    private final int maxSize;

    public CircularQueue(int size) {
        super(size);
        this.numberOfElements = 0;
        this.maxSize = size;
    }

    @Override
    public void enqueue(T element) {
        if (this.isFull()) {
            throw new FullDataStructureException();
        }

        this.numberOfElements++;
        this.array.add(element, this.end);
        this.end = (this.end + 1) % this.maxSize;
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }

        this.numberOfElements--;
        T obj = this.array.get(this.front);
        this.front = (this.front + 1) % this.maxSize;
        return obj;
    }

    @Override
    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }

    @Override
    public boolean isFull() {
        return this.numberOfElements == this.maxSize;
    }
}
