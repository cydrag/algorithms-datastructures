package datastructure.logical;

import datastructure.physical.SingleLinkedList;

public interface BinaryTree<T> extends Tree<T> {

    SingleLinkedList<T> inOrder();
    SingleLinkedList<T> preOrder();
    SingleLinkedList<T> postOrder();

}
