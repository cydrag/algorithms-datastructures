package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.Vertex;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SingleLinkedList;

public abstract class UnweightedGraph<T> implements Graph<Vertex<T>> {

    protected final HashTable<Vertex<T>, Set<Vertex<T>>> adjacencyList;

    public UnweightedGraph() {
        this.adjacencyList = new HashTable<>();
    }

    public static <T> LinkedList<T> searchList(UnweightedGraph<T> graph,
                                               Vertex<T> rootVertex,
                                               Graph.SearchStrategy searchStrategy) {
        if (!graph.contains(rootVertex)) {
            throw new RuntimeException("Graph doesn't contain vertex: " + rootVertex);
        }
        graph.resetVisitation();

        OrderedStructure<Vertex<T>> orderedStructure =
                (searchStrategy == Graph.SearchStrategy.BREADTH_FIRST_SEARCH) ? new DynamicQueue<>() : new DynamicStack<>();
        LinkedList<T> traversalList = new SingleLinkedList<>();

        orderedStructure.add(rootVertex);

        while (!orderedStructure.isEmpty()) {
            Vertex<T> current = orderedStructure.remove();
            if (!current.isVisited()) {
                traversalList.add(current.getData());
                current.setVisited(true);
            }

            for (Vertex<T> neighbour : graph.adjacencyList.get(current)) {
                if (!neighbour.isVisited()) {
                    orderedStructure.add(neighbour);
                }
            }
        }

        graph.resetVisitation();
        return traversalList;
    }

    public LinkedList<Vertex<T>> shortestPathBreadthFirstSearch(Vertex<T> startVertex, Vertex<T> endVertex) {
        if (!this.adjacencyList.contains(startVertex)) {
            throw new RuntimeException("Vertex " + startVertex + " with value " + startVertex.getData() + " doesn't exist in graph.");
        }
        if (!this.adjacencyList.contains(endVertex)) {
            throw new RuntimeException("Vertex " + endVertex + " with value " + endVertex.getData() + " doesn't exist in graph.");
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

        LinkedList<Vertex<T>> shortestPath = new SingleLinkedList<>();

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
            throw new RuntimeException("Vertex " + vertex + " with value " + vertex.getData() + " doesn't exist in graph.");
        }
        else if (!this.adjacencyList.contains(neighbourVertex)) {
            throw new RuntimeException("Vertex " + neighbourVertex + " with value " + neighbourVertex.getData() + " doesn't exist in graph.");
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
        this.adjacencyList.remove(vertex);
    }

    @Override
    public boolean contains(Vertex<T> vertex) {
        return this.adjacencyList.contains(vertex);
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
