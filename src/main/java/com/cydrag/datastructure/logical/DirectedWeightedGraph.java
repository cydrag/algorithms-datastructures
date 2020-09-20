package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.Vertex;

public class DirectedWeightedGraph<T> extends WeightedGraph<T> {

    public DirectedWeightedGraph() {
        super();
    }

    @Override
    protected void addEdgeHook(Vertex<T> vertex, Vertex<T> neighbourVertex, Integer weight) {
        this.adjacencyList.get(vertex).add(neighbourVertex, weight);
    }
}
