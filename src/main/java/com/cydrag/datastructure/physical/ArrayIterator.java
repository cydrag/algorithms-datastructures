package com.cydrag.datastructure.physical;

public interface ArrayIterator<T> extends BidirectionalIterator<T> {
    void setIndex(int index);
}
