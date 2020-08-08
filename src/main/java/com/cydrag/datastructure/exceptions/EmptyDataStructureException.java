package com.cydrag.datastructure.exceptions;

public class EmptyDataStructureException extends RuntimeException {

    public EmptyDataStructureException() {
        super("Unable to perform operation. Data structure is empty.");
    }

    public EmptyDataStructureException(String message) {
        super(message);
    }
}
