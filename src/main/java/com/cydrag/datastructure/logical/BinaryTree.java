package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.physical.LinkedList;

public interface BinaryTree<T> extends Tree<T> {
    boolean isFull();
    boolean isStrict();
    boolean isComplete();
    LinkedList<T> inOrder();
    LinkedList<T> preOrder();
    LinkedList<T> postOrder();
}
