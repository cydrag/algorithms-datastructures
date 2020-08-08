package com.cydrag.datastructure.logical;

public interface Stack<T> extends Orderable<T> {

    @Override
    default void add(T value) {
        this.push(value);
    }

    @Override
    default T remove() {
        return this.pop();
    }

    void push(T value);
    T pop();
}
