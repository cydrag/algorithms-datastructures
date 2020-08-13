package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.ExpendableDataStructure;

public interface Graph<T> extends ExpendableDataStructure<T> {

    boolean breadthFirstSearch(T value);

}
