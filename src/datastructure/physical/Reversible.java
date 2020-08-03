package datastructure.physical;

import java.util.Iterator;

public interface Reversible<T> extends Iterable<T> {
    Iterator<T> reverseIterator();
}
