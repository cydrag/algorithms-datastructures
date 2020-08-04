package datastructure.logical;

import java.util.Comparator;

public class PriorityQueue<T extends Comparable<? super T>> implements Queue<T> {

    private final Comparator<T> comparator;

    public PriorityQueue() {
        this.comparator = null;
    }

    public PriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void enqueue(T element) {

    }

    @Override
    public T dequeue() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void destroy() {

    }
}
