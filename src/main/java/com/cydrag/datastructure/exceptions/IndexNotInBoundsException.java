package com.cydrag.datastructure.exceptions;

public class IndexNotInBoundsException extends RuntimeException {

    public IndexNotInBoundsException() {
        super("Index not in bounds.");
    }

    public IndexNotInBoundsException(String message) {
        super(message);
    }

    public IndexNotInBoundsException(int index, int size) {
        this("Index " + index + " is not in bounds. (Data structure size: " + size + ")");
    }
}
