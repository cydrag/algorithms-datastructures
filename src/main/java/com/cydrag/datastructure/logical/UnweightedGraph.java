package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.Vertex;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SingleLinkedList;

public abstract class UnweightedGraph<T> implements Graph<T> {

    protected final HashTable<Vertex<T>, Set<Vertex<T>>> adjacencyList;

    public UnweightedGraph() {
        this.adjacencyList = new HashTable<>();
    }

    Vertex<T> getVertexRefByValue(T value) {
        Vertex<T> vertexToFind = new Vertex<>(value);
        Vertex<T> found = null;

        for (Vertex<T> tempVert : this.adjacencyList.keys()) {
            if (tempVert.equals(vertexToFind)) {
                found = tempVert;
                break;
            }
        }

        return found;
    }

    public static <T> LinkedList<T> bfs(UnweightedGraph<T> graph, T rootVertex) {
        graph.resetVisitation();

        LinkedList<T> traversalList = new SingleLinkedList<>();

        Vertex<T> found = graph.getVertexRefByValue(rootVertex);

        if (found == null) {
            return traversalList;
        }

        Queue<Vertex<T>> queue = new DynamicQueue<>();
        queue.enqueue(found);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.dequeue();
            if (!current.isVisited()) {
                traversalList.add(current.getData());
                current.setVisited(true);
            }

            for (Vertex<T> neighbour : graph.adjacencyList.get(current)) {
                if (!neighbour.isVisited()) {
                    queue.add(neighbour);
                }
            }
        }

        graph.resetVisitation();
        return traversalList;
    }

    public static <T> LinkedList<T> dfs(UnweightedGraph<T> graph, T rootVertex) {
        graph.resetVisitation();

        LinkedList<T> traversalList = new SingleLinkedList<>();

        Vertex<T> found = graph.getVertexRefByValue(rootVertex);

        if (found == null) {
            return traversalList;
        }

        Stack<Vertex<T>> stack = new DynamicStack<>();
        stack.push(found);

        while (!stack.isEmpty()) {
            Vertex<T> current = stack.pop();
            if (!current.isVisited()) {
                traversalList.add(current.getData());
                current.setVisited(true);
            }

            for (Vertex<T> neighbour : graph.adjacencyList.get(current)) {
                if (!neighbour.isVisited()) {
                    stack.push(neighbour);
                }
            }
        }

        graph.resetVisitation();
        return traversalList;
    }

    private void resetVisitation() {
        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            vertex.setVisited(false);
        }
    }

    public abstract void addEdge(T vertex, T neighbourVertex);

    @Override
    public void add(T vertex) {
        Vertex<T> v = new Vertex<>(vertex);
        this.adjacencyList.add(v, new Set<>());
    }

    @Override
    public void remove(T value) {
        Vertex<T> vertex = new Vertex<>(value);

        for (Set<Vertex<T>> edge : this.adjacencyList.values()) {
            edge.remove(vertex);
        }

        this.adjacencyList.remove(vertex);
    }

    @Override
    public boolean contains(T vertex) {
        return this.getVertexRefByValue(vertex) != null;
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
