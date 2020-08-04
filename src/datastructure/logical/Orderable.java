package datastructure.logical;

import datastructure.DataStructure;
import datastructure.exceptions.ForbiddenOperationException;

public interface Orderable<T> extends DataStructure<T> {
    @Override
    @Deprecated
    default void remove(T element) {
        throw new ForbiddenOperationException();
    }

    T remove();
    T peek();
}
