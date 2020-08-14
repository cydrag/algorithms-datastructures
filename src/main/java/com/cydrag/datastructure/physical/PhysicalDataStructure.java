package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.ExpendableDataStructure;

public interface PhysicalDataStructure<T> extends ExpendableDataStructure<T>, Iterable<T> {
    void add(T value, int index);
    T get(int index);
    void remove(int index);
}
