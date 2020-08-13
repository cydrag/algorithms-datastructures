package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.Vertex;

public class DirectedUnweightedGraph<T> extends UnweightedGraph<T> {

    public DirectedUnweightedGraph() {
        super();
    }

    @Override
    protected void addEdgeHook(Vertex<T> vertex, Vertex<T> neighbourVertex) {
        this.adjacencyList.get(vertex).add(neighbourVertex);
    }
}
