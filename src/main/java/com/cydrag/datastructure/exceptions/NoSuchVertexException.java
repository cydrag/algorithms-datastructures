package com.cydrag.datastructure.exceptions;

import com.cydrag.datastructure.nodes.Vertex;

public class NoSuchVertexException extends RuntimeException {

    public NoSuchVertexException() {
        super("Vertex doesn't exist in graph.");
    }

    public NoSuchVertexException(String message) {
        super(message);
    }

    public NoSuchVertexException(Vertex<?> vertex) {
        super("Vertex " + vertex + " with value " + vertex.getData() + " doesn't exist in graph.");
    }
}
