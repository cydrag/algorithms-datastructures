package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.ExpendableDataStructure;
import com.cydrag.datastructure.physical.LinkedList;

public interface Tree<T> extends ExpendableDataStructure<T> {
    LinkedList<T> levelOrder();
}
