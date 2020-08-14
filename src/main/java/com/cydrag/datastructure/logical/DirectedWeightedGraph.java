package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.WeightedVertex;

public class DirectedWeightedGraph<T> extends WeightedGraph<T> {

    public DirectedWeightedGraph() {
        super();
    }

    @Override
    protected void addEdgeHook(WeightedVertex<T> vertex, WeightedVertex<T> neighbourVertex, Integer weight) {
        this.adjacencyList.get(vertex).add(neighbourVertex, weight);
    }
}
