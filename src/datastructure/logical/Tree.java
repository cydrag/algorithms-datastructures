package datastructure.logical;

import datastructure.physical.SingleLinkedList;

public interface Tree<T> {

    void add(T element);
    void remove(T element);
    boolean contains(T element);
    SingleLinkedList<T> levelOrder();
    boolean isEmpty();
    void destroy();
}
