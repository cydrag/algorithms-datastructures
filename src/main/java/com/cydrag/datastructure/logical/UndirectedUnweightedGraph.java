package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.Vertex;

public class UndirectedUnweightedGraph<T> extends UnweightedGraph<T> {

    public UndirectedUnweightedGraph() {
        super();
    }

    @Override
    protected void addEdgeHook(Vertex<T> vertex, Vertex<T> neighbourVertex) {
        this.adjacencyList.get(vertex).add(neighbourVertex);
        this.adjacencyList.get(neighbourVertex).add(vertex);
    }
}
