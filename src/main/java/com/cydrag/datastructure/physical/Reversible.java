package com.cydrag.datastructure.physical;

import java.util.Iterator;

public interface Reversible<T> extends Iterable<T> {
    Iterator<T> reverseIterator();
}
