package datastructure.physical;

import java.util.Iterator;

public interface ReverseIterator<T> extends Iterator<T> {
    T previous();
    boolean hasPrevious();
}
