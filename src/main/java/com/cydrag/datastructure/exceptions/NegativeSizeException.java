package com.cydrag.datastructure.exceptions;

public class NegativeSizeException extends RuntimeException {

    private static final String INITIAL_MESSAGE = "Negative data structure size.";

    public NegativeSizeException() {
        super(INITIAL_MESSAGE);
    }

    public NegativeSizeException(String message) {
        super(message);
    }

    public NegativeSizeException(int enteredSize) {
        this(INITIAL_MESSAGE + " Input: " + enteredSize);
    }
}
