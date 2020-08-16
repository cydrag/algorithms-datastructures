package com.cydrag.datastructure.physical;

public interface Reversible<T> extends Iterable<T> {
    BidirectionalIterator<T> bidirectionalIterator();
}
