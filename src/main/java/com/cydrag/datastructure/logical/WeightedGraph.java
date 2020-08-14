package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.NegativeValueException;
import com.cydrag.datastructure.exceptions.NoSuchVertexException;
import com.cydrag.datastructure.nodes.WeightedVertex;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SingleLinkedList;

public abstract class WeightedGraph<T> implements Graph<WeightedVertex<T>> {

    protected final HashTable<WeightedVertex<T>, HashTable<WeightedVertex<T>, Integer>> adjacencyList;

    public WeightedGraph() {
        this.adjacencyList = new HashTable<>();
    }

    public LinkedList<WeightedVertex<T>> searchList(WeightedVertex<T> rootVertex, Graph.SearchStrategy searchStrategy) {
        if (!this.contains(rootVertex)) {
            throw new NoSuchVertexException(rootVertex);
        }
        this.resetVisitation();

        OrderedStructure<WeightedVertex<T>> orderedStructure =
                (searchStrategy == Graph.SearchStrategy.BREADTH_FIRST_SEARCH) ? new DynamicQueue<>() : new DynamicStack<>();
        LinkedList<WeightedVertex<T>> traversalList = new SingleLinkedList<>();

        orderedStructure.add(rootVertex);

        while (!orderedStructure.isEmpty()) {
            WeightedVertex<T> current = orderedStructure.remove();
            if (!current.isVisited()) {
                traversalList.add(current);
                current.setVisited(true);
            }

            for (WeightedVertex<T> neighbour : this.adjacencyList.get(current).keys()) {
                if (!neighbour.isVisited()) {
                    orderedStructure.add(neighbour);
                }
            }
        }

        this.resetVisitation();
        return traversalList;
    }

    public LinkedList<WeightedVertex<T>> shortestPathDijkstra(WeightedVertex<T> startVertex, WeightedVertex<T> endVertex) {
        if (!this.adjacencyList.contains(startVertex)) {
            throw new NoSuchVertexException(startVertex);
        }
        if (!this.adjacencyList.contains(endVertex)) {
            throw new NoSuchVertexException(endVertex);
        }

        this.resetVisitation();
        this.resetParents();

        for (HashTable<WeightedVertex<T>, Integer> hashTable : this.adjacencyList.values()) {
            for (Integer num : hashTable.values()) {
                if (num < 0) {
                    throw new NegativeValueException("Dijkstra algorithm cannot work with negative edge weights. Found weight value: " + num);
                }
            }
        }

        for (WeightedVertex<T> weightedVertex : this.adjacencyList.keys()) {
            weightedVertex.setDistance(Integer.MAX_VALUE);
        }
        startVertex.setDistance(0);

        PriorityQueue<WeightedVertex<T>> distanceVerticesQueue =
                new PriorityQueue<>((o1, o2) -> o2.getDistance() - o1.getDistance());

        for (WeightedVertex<T> vertex : this.adjacencyList.keys()) {
            distanceVerticesQueue.add(vertex);
        }

        while (!distanceVerticesQueue.isEmpty()) {
            WeightedVertex<T> current = distanceVerticesQueue.remove();

            for (WeightedVertex<T> neighbour : this.adjacencyList.get(current).keys()) {
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
        LinkedList<WeightedVertex<T>> shortestPath = new SingleLinkedList<>();

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

    private void resetParents() {
        for (WeightedVertex<T> vertex : this.adjacencyList.keys()) {
            vertex.setParent(null);
        }
    }

    private void resetVisitation() {
        for (WeightedVertex<T> vertex : this.adjacencyList.keys()) {
            vertex.setVisited(false);
        }
    }

    protected abstract void addEdgeHook(WeightedVertex<T> vertex, WeightedVertex<T> neighbourVertex, Integer weight);

    public void addEdge(WeightedVertex<T> vertex, WeightedVertex<T> neighbourVertex, Integer weight) {
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

    @Override
    public void add(WeightedVertex<T> vertex) {
        if (!this.adjacencyList.contains(vertex)) {
            this.adjacencyList.add(vertex, new HashTable<>());
        }
    }

    @Override
    public void remove(WeightedVertex<T> vertex) {
        for (HashTable<WeightedVertex<T>, Integer> neighbour : this.adjacencyList.values()) {
            neighbour.remove(vertex);
        }
        this.adjacencyList.remove(vertex);
    }

    @Override
    public boolean contains(WeightedVertex<T> vertex) {
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
