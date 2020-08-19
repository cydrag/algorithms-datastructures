package com.cydrag.datastructure.logical;

public interface Queue<T> extends OrderedDataStructure<T> {
    @Override
    default void add(T value) {
        this.enqueue(value);
    }

    @Override
    default T remove() {
        return this.dequeue();
    }

    void enqueue(T value);
    T dequeue();
}
