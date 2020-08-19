package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.NoSuchVertexException;
import com.cydrag.datastructure.nodes.Vertex;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SinglyLinkedList;

public abstract class UnweightedGraph<T> implements Graph<Vertex<T>> {

    protected final HashTable<Vertex<T>, Set<Vertex<T>>> adjacencyList;

    public UnweightedGraph() {
        this.adjacencyList = new HashTable<>();
    }

    public LinkedList<Vertex<T>> searchList(Vertex<T> rootVertex, Graph.SearchStrategy searchStrategy) {

        if (!this.contains(rootVertex)) {
            throw new NoSuchVertexException(rootVertex);
        }
        this.resetVisitation();

        OrderedDataStructure<Vertex<T>> orderedDataStructure =
                (searchStrategy == Graph.SearchStrategy.BREADTH_FIRST_SEARCH) ? new DynamicQueue<>() : new DynamicStack<>();
        LinkedList<Vertex<T>> traversalList = new SinglyLinkedList<>();

        orderedDataStructure.add(rootVertex);

        while (!orderedDataStructure.isEmpty()) {
            Vertex<T> current = orderedDataStructure.remove();
            if (!current.isVisited()) {
                traversalList.add(current);
                current.setVisited(true);
            }

            for (Vertex<T> neighbour : this.adjacencyList.get(current)) {
                if (!neighbour.isVisited()) {
                    orderedDataStructure.add(neighbour);
                }
            }
        }

        this.resetVisitation();
        return traversalList;
    }

    public LinkedList<Vertex<T>> shortestPathBreadthFirstSearch(Vertex<T> startVertex, Vertex<T> endVertex) {
        if (!this.adjacencyList.contains(startVertex)) {
            throw new NoSuchVertexException(startVertex);
        }
        if (!this.adjacencyList.contains(endVertex)) {
            throw new NoSuchVertexException(endVertex);
        }

        boolean found = false;
        boolean first = true;

        this.resetVisitation();
        this.resetParents();

        Queue<Vertex<T>> queue = new DynamicQueue<>();
        queue.enqueue(startVertex);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.dequeue();
            if (!first) {
                current.setVisited(true);
            }
            first = false;

            for (Vertex<T> neighbour : this.adjacencyList.get(current)) {
                neighbour.setParent(current);
                if (neighbour == endVertex) {
                    found = true;
                    queue.clear();
                    break;
                }
                else {
                    if (!neighbour.isVisited()) {
                        queue.enqueue(neighbour);
                    }
                }
            }
        }

        this.resetVisitation();

        LinkedList<Vertex<T>> shortestPath = new SinglyLinkedList<>();

        if (found) {
            while (endVertex != null) {
                if (!endVertex.isVisited()) {
                    shortestPath.addAtStart(endVertex);
                    endVertex.setVisited(true);
                }
                else {
                    break;
                }
                endVertex = endVertex.getParent();
            }
        }

        this.resetVisitation();
        this.resetParents();
        return shortestPath;
    }

    private void resetVisitation() {
        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            vertex.setVisited(false);
        }
    }

    private void resetParents() {
        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            vertex.setParent(null);
        }
    }

    protected abstract void addEdgeHook(Vertex<T> vertex, Vertex<T> neighbourVertex);

    public void addEdge(Vertex<T> vertex, Vertex<T> neighbourVertex) {
        if (!this.adjacencyList.contains(vertex)) {
            throw new NoSuchVertexException(vertex);
        }
        else if (!this.adjacencyList.contains(neighbourVertex)) {
            throw new NoSuchVertexException(neighbourVertex);
        }
        else {
            this.addEdgeHook(vertex, neighbourVertex);
        }
    }

    @Override
    public void add(Vertex<T> vertex) {
        if (!this.adjacencyList.contains(vertex)) {
            this.adjacencyList.add(vertex, new Set<>());
        }
    }

    @Override
    public void remove(Vertex<T> vertex) {
        for (Set<Vertex<T>> neighbour : this.adjacencyList.values()) {
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
