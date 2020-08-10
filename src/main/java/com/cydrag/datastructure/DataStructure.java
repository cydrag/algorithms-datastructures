package com.cydrag.datastructure;

public interface DataStructure<T> {
    void remove(T value);
    boolean contains(T value);
    boolean isEmpty();
    void clear();
}
