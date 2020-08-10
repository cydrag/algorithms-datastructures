package com.cydrag.datastructure;

public interface ExpendableDataStructure<T> extends DataStructure<T> {
    void add(T value);
}
