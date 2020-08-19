package com.cydrag.datastructure.physical;

public interface Bidirectional<T> extends Iterable<T> {
    BidirectionalIterator<T> bidirectionalIterator();
}
