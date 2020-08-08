package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.DataStructure;
import com.cydrag.datastructure.exceptions.ForbiddenOperationException;

public interface Orderable<T> extends DataStructure<T> {
    @Override
    @Deprecated
    default void remove(T value) {
        throw new ForbiddenOperationException();
    }

    T remove();
    T peek();
}
