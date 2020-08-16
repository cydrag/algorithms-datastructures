package com.cydrag.datastructure.physical;

import java.util.Iterator;

public interface Loopable<T> {
    Iterator<T> loopIterator();
}
