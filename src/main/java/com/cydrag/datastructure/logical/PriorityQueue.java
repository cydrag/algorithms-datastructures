package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.EmptyDataStructureException;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SingleLinkedList;

import java.util.Comparator;

public class PriorityQueue<T extends Comparable<? super T>> implements Queue<T> {

    private final LinkedList<T> priorityQueue;
    private final Comparator<T> comparator;

    public PriorityQueue() {
        this(Comparator.naturalOrder());
    }

    public PriorityQueue(Comparator<T> comparator) {
        this.priorityQueue = new SingleLinkedList<>();
        this.comparator = comparator;
    }

    private void checkEmpty() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
    }

    @Override
    public void enqueue(T value) {

        if (value == null) {
            priorityQueue.addAtEnd(null);
        }
        else {
            if (priorityQueue.isEmpty()) {
                priorityQueue.addAtStart(value);
            }
            for (int i = 0; i < priorityQueue.size(); i++) {
                if (priorityQueue.get(i) != null) {
                    if (comparator.compare(priorityQueue.get(i), value) > 0) {
                        priorityQueue.add(value, i);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void remove(T value) {
        priorityQueue.remove(value);
    }

    @Override
    public T dequeue() {
        T value = priorityQueue.getAtStart();
        priorityQueue.removeAtStart();
        return value;
    }

    @Override
    public T peek() {
        return priorityQueue.getAtStart();
    }

    @Override
    public boolean contains(T value) {
        return priorityQueue.contains(value);
    }

    @Override
    public int size() {
        return priorityQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    @Override
    public void clear() {
        priorityQueue.clear();
    }
}
