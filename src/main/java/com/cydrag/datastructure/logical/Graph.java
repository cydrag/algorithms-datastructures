package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.ExpendableDataStructure;

public interface Graph<T> extends ExpendableDataStructure<T> {
    enum SearchStrategy {
        BREADTH_FIRST_SEARCH,
        DEPTH_FIRST_SEARCH
    }
}
