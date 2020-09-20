package com.cydrag.datastructure.nodes;

import java.util.Comparator;

public class Vertex<T> implements Comparable<Vertex<T>> {

    private int distance;
    private final T data;
    private boolean visited;
    private Vertex<T> parent;

    public Vertex(T data) {
        this.data = data;
        this.visited = false;
        this.parent = null;
        this.distance = 0;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public T getData() {
        return data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex<T> getParent() {
        return parent;
    }

    public void setParent(Vertex<T> parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Vertex<T> o) {
        Comparator<Vertex<T>> comparator = Comparator.comparing(Vertex::getDistance);
        return comparator.compare(this, o);
    }
}
