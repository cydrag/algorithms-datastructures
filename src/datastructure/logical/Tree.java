package datastructure.logical;

import datastructure.DataStructure;
import datastructure.physical.LinkedList;

public interface Tree<T> extends DataStructure<T> {
    LinkedList<T> levelOrder();
}
