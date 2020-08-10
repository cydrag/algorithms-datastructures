package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.ExpendableDataStructure;
import com.cydrag.datastructure.exceptions.ForbiddenOperationException;

public interface OrderedStructure<T> extends ExpendableDataStructure<T> {
    @Override
    @Deprecated
    default void remove(T value) {
        throw new ForbiddenOperationException();
    }

    T remove();
    T peek();
}
