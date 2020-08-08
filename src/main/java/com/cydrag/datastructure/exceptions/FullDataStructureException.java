package com.cydrag.datastructure.exceptions;

public class FullDataStructureException extends RuntimeException {

    public FullDataStructureException() {
        super("Unable to perform operation. Data structure is full.");
    }

    public FullDataStructureException(String message) {
        super(message);
    }
}
