package com.cydrag.datastructure;

public interface DataStructure<T> {

    void add(T value);

    void remove(T value);

    boolean contains(T value);

    int size();

    boolean isEmpty();

    void clear();

}

