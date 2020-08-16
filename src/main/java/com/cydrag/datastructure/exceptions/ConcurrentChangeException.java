package com.cydrag.datastructure.exceptions;

public class ConcurrentChangeException extends RuntimeException {

    public ConcurrentChangeException() {
        super("Concurrent change of data in data structure.");
    }

    public ConcurrentChangeException(String message) {
        super(message);
    }
}
