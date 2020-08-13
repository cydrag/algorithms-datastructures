package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.Vertex;

public class UndirectedUnweightedGraph<T> extends UnweightedGraph<T> {

    public UndirectedUnweightedGraph() {
        super();
    }

    @Override
    public void addEdge(T vertex, T neighbourVertex) {
        Vertex<T> v = getVertexRefByValue(vertex);
        Vertex<T> neighbour = getVertexRefByValue(neighbourVertex);

        if (v == null) {
            throw new RuntimeException("Vertex with value " + vertex + " doesn't exist in graph.");
        }
        else if (neighbour == null) {
            throw new RuntimeException("Neighbour vertex with value " + neighbourVertex + " doesn't exist in graph.");
        }
        else {
            this.adjacencyList.get(v).add(neighbour);
            this.adjacencyList.get(neighbour).add(v);
        }
    }

    @Override
    public boolean breadthFirstSearch(T value) {
        return false;
    }
}
