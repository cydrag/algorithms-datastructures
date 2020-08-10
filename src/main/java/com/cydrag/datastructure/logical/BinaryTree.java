package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.physical.LinkedList;

// TODO: Implement get method
public interface BinaryTree<T> extends Tree<T> {
    boolean isFull();
    boolean isStrict();
    LinkedList<T> inOrder();
    LinkedList<T> preOrder();
    LinkedList<T> postOrder();
}
