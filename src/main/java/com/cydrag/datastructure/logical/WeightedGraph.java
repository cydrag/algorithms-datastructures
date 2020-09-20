package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.NegativeValueException;
import com.cydrag.datastructure.exceptions.NoSuchVertexException;
import com.cydrag.datastructure.nodes.Vertex;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SinglyLinkedList;

public abstract class WeightedGraph<T> extends Graph<T> {

    public WeightedGraph() {
        super();
    }

    public LinkedList<Vertex<T>> shortestPathDijkstra(Vertex<T> startVertex, Vertex<T> endVertex) {
        if (!this.adjacencyList.contains(startVertex)) {
            throw new NoSuchVertexException(startVertex);
        }
        if (!this.adjacencyList.contains(endVertex)) {
            throw new NoSuchVertexException(endVertex);
        }

        this.resetVisitation();
        this.resetParents();

        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            HashTable<Vertex<T>, Integer> edges = this.adjacencyList.get(vertex);

            for (Integer num : edges.values()) {
                if (num < 0) {
                    throw new NegativeValueException("Dijkstra algorithm cannot work with negative edge weights. Found weight value: " + num);
                }
            }
        }

        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }
        startVertex.setDistance(0);

        PriorityQueue<Vertex<T>> distanceVerticesQueue =
                new PriorityQueue<>();

        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            distanceVerticesQueue.add(vertex);
        }

        while (!distanceVerticesQueue.isEmpty()) {
            Vertex<T> current = distanceVerticesQueue.remove();

            for (Vertex<T> neighbour : this.adjacencyList.get(current).keys()) {
                if (!neighbour.isVisited()) {
                    int weight = current.getDistance() + this.adjacencyList.get(current).get(neighbour);

                    if (neighbour.getDistance() > weight) {
                        neighbour.setParent(current);
                        neighbour.setDistance(weight);
                        distanceVerticesQueue.remove(neighbour);
                        distanceVerticesQueue.enqueue(neighbour);
                    }
                }
            }
            current.setVisited(true);
        }

        this.resetVisitation();
        LinkedList<Vertex<T>> shortestPath = new SinglyLinkedList<>();

        if ((endVertex.getDistance() == Integer.MAX_VALUE) || (startVertex == endVertex)) {
            return shortestPath;
        }
        else {
            while ((endVertex.getParent() != null) && (!endVertex.isVisited())) {
                shortestPath.addAtStart(endVertex);
                endVertex.setVisited(true);
                endVertex = endVertex.getParent();
            }
            shortestPath.addAtStart(endVertex);
        }

        this.resetVisitation();
        this.resetParents();
        if (startVertex != endVertex) {
            shortestPath.clear();
        }
        return shortestPath;
    }


    public LinkedList<Vertex<T>> shortestPathBellmanFord(Vertex<T> startVertex, Vertex<T> endVertex) {
        if (!this.adjacencyList.contains(startVertex)) {
            throw new NoSuchVertexException(startVertex);
        }
        if (!this.adjacencyList.contains(endVertex)) {
            throw new NoSuchVertexException(endVertex);
        }

        this.resetVisitation();
        this.resetParents();

        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            vertex.setDistance(Integer.MAX_VALUE);
        }
        startVertex.setDistance(0);

        LinkedList<HashTable.Pair<Vertex<T>, HashTable.Pair<Vertex<T>, Integer>>> allEdges = this.edges();

        final int maxIterations = this.adjacencyList.keys().size() - 1;

        for (int i = 0; i < maxIterations; i++) {
            for (HashTable.Pair<Vertex<T>, HashTable.Pair<Vertex<T>, Integer>> edge : allEdges) {
                Vertex<T> sourceVertex = edge.getKey();
                Vertex<T> targetVertex = edge.getValue().getKey();
                Integer edgeWeight = edge.getValue().getValue();

                if (sourceVertex.getDistance() != Integer.MAX_VALUE) {
                    if (sourceVertex.getDistance() + edgeWeight < targetVertex.getDistance()) {
                        targetVertex.setDistance(sourceVertex.getDistance() + edgeWeight);
                        targetVertex.setParent(sourceVertex);
                    }
                }
            }
        }

        for (HashTable.Pair<Vertex<T>, HashTable.Pair<Vertex<T>, Integer>> edge : allEdges) {
            Vertex<T> sourceVertex = edge.getKey();
            Vertex<T> targetVertex = edge.getValue().getKey();
            Integer edgeWeight = edge.getValue().getValue();

            if (sourceVertex.getDistance() != Integer.MAX_VALUE) {
                if (sourceVertex.getDistance() + edgeWeight < targetVertex.getDistance()) {
                    throw new NegativeValueException("Graph contains negative weight cycle.");
                }
            }
        }

        this.resetVisitation();
        LinkedList<Vertex<T>> shortestPath = new SinglyLinkedList<>();

        if ((endVertex.getDistance() == Integer.MAX_VALUE) || (startVertex == endVertex)) {
            return shortestPath;
        }
        else {
            while ((endVertex.getParent() != null) && (!endVertex.isVisited())) {
                shortestPath.addAtStart(endVertex);
                endVertex.setVisited(true);
                endVertex = endVertex.getParent();
            }
            shortestPath.addAtStart(endVertex);
        }

        this.resetVisitation();
        this.resetParents();
        if (startVertex != endVertex) {
            shortestPath.clear();
        }
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

    public LinkedList<HashTable.Pair<Vertex<T>, HashTable.Pair<Vertex<T>, Integer>>> edges() {
        LinkedList<HashTable.Pair<Vertex<T>, HashTable.Pair<Vertex<T>, Integer>>> allEdges
                = new SinglyLinkedList<>();

        for (Vertex<T> vertex : this.adjacencyList.keys()) {
            HashTable<Vertex<T>, Integer> edges = this.adjacencyList.get(vertex);

            for (HashTable.Pair<Vertex<T>, Integer> edge : edges.pairs()) {
                allEdges.add(new HashTable.Pair<>(vertex, new HashTable.Pair<>(edge.getKey(), edge.getValue())));
            }
        }

        return allEdges;
    }

    protected abstract void addEdgeHook(Vertex<T> vertex, Vertex<T> neighbourVertex, Integer weight);

    public void addEdge(Vertex<T> vertex, Vertex<T> neighbourVertex, Integer weight) {
        if (!this.adjacencyList.contains(vertex)) {
            throw new NoSuchVertexException(vertex);
        }
        else if (!this.adjacencyList.contains(neighbourVertex)) {
            throw new NoSuchVertexException(neighbourVertex);
        }
        else {
            this.addEdgeHook(vertex, neighbourVertex, weight);
        }
    }
}
