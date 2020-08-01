package datastructure.logical;

import datastructure.physical.SingleLinkedList;

public interface Tree<T> {

    boolean contains(T data);
    void remove(T data);
    SingleLinkedList<T> levelOrder();
    void delete();
}
