package datastructure.physical;

import datastructure.DataStructure;

public interface PhysicalDataStructure<T> extends DataStructure<T>, Iterable<T> {
    int length();
    void add(T element, int index);
    T get(int index);
    void remove(int index);
}
