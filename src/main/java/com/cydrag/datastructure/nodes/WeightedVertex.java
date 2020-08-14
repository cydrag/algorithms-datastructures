package com.cydrag.datastructure.nodes;

import java.util.Comparator;

public class WeightedVertex<T> implements Comparable<WeightedVertex<T>> {

    private int distance;
    private final T data;
    private boolean visited;
    private WeightedVertex<T> parent;

    public WeightedVertex(T data) {
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

    public WeightedVertex<T> getParent() {
        return parent;
    }

    public void setParent(WeightedVertex<T> parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(WeightedVertex<T> o) {
        Comparator<WeightedVertex<T>> comparator = Comparator.comparing(WeightedVertex::getDistance);
        return comparator.compare(this, o);
    }
}
