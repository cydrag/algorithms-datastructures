package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.DataStructure;

public interface Graph<T> extends DataStructure<T> {
    enum SearchStrategy {
        BREADTH_FIRST_SEARCH,
        DEPTH_FIRST_SEARCH
    }
}
