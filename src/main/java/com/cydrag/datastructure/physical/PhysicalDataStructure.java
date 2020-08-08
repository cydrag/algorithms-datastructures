package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.DataStructure;

public interface PhysicalDataStructure<T> extends DataStructure<T>, Iterable<T> {
    int length();
    void add(T value, int index);
    T get(int index);
    void remove(int index);
}
