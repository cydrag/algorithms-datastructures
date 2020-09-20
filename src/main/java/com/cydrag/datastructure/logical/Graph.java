package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.DataStructure;
import com.cydrag.datastructure.exceptions.NoSuchVertexException;
import com.cydrag.datastructure.nodes.Vertex;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SinglyLinkedList;

public abstract class Graph<T> implements DataStructure<Vertex<T>> {

    final HashTable<Vertex<T>, HashTable<Vertex<T>, Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashTable<>();
    }

    public enum SearchStrategy {
        BREADTH_FIRST_SEARCH,
        DEPTH_FIRST_SEARCH
    }

    public LinkedList<Vertex<T>> searchList(Vertex<T> rootVertex, Graph.SearchStrategy searchStrategy) {
        if (!this.contains(rootVertex)) {
            throw new NoSuchVertexException(rootVertex);
        }
        this.resetVisitation();

        OrderedDataStructure<Vertex<T>> orderedDataStructure =
                (searchStrategy == Graph.SearchStrategy.BREADTH_FIRST_SEARCH) ?
                        new DynamicQueue<>() : new DynamicStack<>();

        LinkedList<Vertex<T>> traversalList = new SinglyLinkedList<>();

        orderedDataStructure.add(rootVertex);

        while (!orderedDataStructure.isEmpty()) {
            Vertex<T> current = orderedDataStructure.remove();
            if (!current.isVisited()) {
                traversalList.add(current);
                current.setVisited(true);
            }

            for (Vertex<T> neighbour : this.adjacencyList.get(current).keys()) {
                if (!neighbour.isVisited()) {
                    orderedDataStructure.add(neighbour);
                }
            }
        }

        this.resetVisitation();
        return traversalList;
    }

    public LinkedList<Vertex<T>> vertices() {
        return this.adjacencyList.keys();
    }

    void resetParents() {
        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            vertex.setParent(null);
        }
    }

    void resetVisitation() {
        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            vertex.setVisited(false);
        }
    }

    protected abstract boolean isComplete();

    @SafeVarargs
    public final void addAll(Vertex<T>... vertices) {
        for (Vertex<T> vertex : vertices) {
            this.add(vertex);
        }
    }

    @Override
    public void add(Vertex<T> vertex) {
        if (!this.adjacencyList.contains(vertex)) {
            this.adjacencyList.add(vertex, new HashTable<>());
        }
    }

    @Override
    public void remove(Vertex<T> vertex) {
        for (HashTable<Vertex<T>, Integer> neighbour : this.adjacencyList.values()) {
            neighbour.remove(vertex);
        }
        this.adjacencyList.remove(vertex);
    }

    @Override
    public boolean contains(Vertex<T> vertex) {
        return this.adjacencyList.contains(vertex);
    }

    @Override
    public int size() {
        return this.adjacencyList.keys().size();
    }

    @Override
    public boolean isEmpty() {
        return this.adjacencyList.isEmpty();
    }

    @Override
    public void clear() {
        this.adjacencyList.clear();
    }
}
