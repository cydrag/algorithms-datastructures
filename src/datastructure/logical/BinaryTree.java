package datastructure.logical;

import datastructure.physical.LinkedList;

public interface BinaryTree<T> extends Tree<T> {
    boolean isFull();
    boolean isStrict();
    LinkedList<T> inOrder();
    LinkedList<T> preOrder();
    LinkedList<T> postOrder();
}
