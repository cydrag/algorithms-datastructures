package datastructure.logical;

import datastructure.physical.SingleLinkedList;

public interface Tree<T> {

    boolean contains(T element);
    void remove(T element);
    SingleLinkedList<T> levelOrder();
    void destroy();
}
