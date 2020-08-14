package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.WeightedVertex;

public class UndirectedWeightedGraph<T> extends WeightedGraph<T> {

    public UndirectedWeightedGraph() {
        super();
    }

    @Override
    protected void addEdgeHook(WeightedVertex<T> vertex, WeightedVertex<T> neighbourVertex, Integer weight) {
        this.adjacencyList.get(vertex).add(neighbourVertex, weight);
        this.adjacencyList.get(neighbourVertex).add(vertex, weight);
    }
}
