package datastructure.logical;

import datastructure.DataStructure;
import datastructure.physical.SingleLinkedList;

public interface Tree<T> extends DataStructure<T> {
    SingleLinkedList<T> levelOrder();
}
