package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.DataStructure;
import com.cydrag.datastructure.physical.LinkedList;

public interface Tree<T> extends DataStructure<T> {
    LinkedList<T> levelOrder();
}
