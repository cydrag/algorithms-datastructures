package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.NoSuchVertexException;
import com.cydrag.datastructure.nodes.Vertex;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SinglyLinkedList;

public abstract class UnweightedGraph<T> extends Graph<T> {

    public UnweightedGraph() {
        super();
    }

    public LinkedList<Vertex<T>> shortestPathBreadthFirstSearch(Vertex<T> startVertex, Vertex<T> endVertex) {
        if (!this.adjacencyList.contains(startVertex)) {
            throw new NoSuchVertexException(startVertex);
        }
        if (!this.adjacencyList.contains(endVertex)) {
            throw new NoSuchVertexException(endVertex);
        }

        LinkedList<Vertex<T>> shortestPath = new SinglyLinkedList<>();

        if (startVertex == endVertex) {
            return shortestPath;
        }

        boolean found = false;

        this.resetVisitation();
        this.resetParents();

        Queue<Vertex<T>> queue = new DynamicQueue<>();
        queue.enqueue(startVertex);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.dequeue();
            current.setVisited(true);

            for (Vertex<T> neighbour : this.adjacencyList.get(current).keys()) {
                if (!neighbour.isVisited()) {
                    neighbour.setParent(current);
                }
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

        if (found) {
            while (endVertex != null) {
                shortestPath.addAtStart(endVertex);
                endVertex = endVertex.getParent();
            }
        }

        this.resetVisitation();
        this.resetParents();
        return shortestPath;
    }

    @Override
    public boolean isComplete() {

        int numOfVertices = this.adjacencyList.size();

        if (numOfVertices == 0) {
            return false;
        }
        else if (numOfVertices == 1) {
            return true;
        }
        else {
            int expected = numOfVertices * (numOfVertices - 1);
            return expected == this.edges().size();
        }
    }

    public LinkedList<HashTable.Pair<Vertex<T>, Vertex<T>>> edges() {

        LinkedList<HashTable.Pair<Vertex<T>, Vertex<T>>> allEdges
                = new SinglyLinkedList<>();

        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            HashTable<Vertex<T>, Integer> edges = this.adjacencyList.get(vertex);

            for (HashTable.Pair<Vertex<T>, Integer> edge : edges.pairs()) {
                allEdges.add(new HashTable.Pair<>(vertex, edge.getKey()));
            }
        }

        return allEdges;
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

}
