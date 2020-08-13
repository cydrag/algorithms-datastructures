package com.cydrag.datastructure.nodes;

public class Vertex<T> {

    private final T data;
    private boolean visited;
    private Vertex<T> parent;

    public Vertex(T data) {
        this.data = data;
        this.visited = false;
        this.parent = null;
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
}
