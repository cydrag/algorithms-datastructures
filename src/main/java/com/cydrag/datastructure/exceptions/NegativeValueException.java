package com.cydrag.datastructure.exceptions;

public class NegativeValueException extends RuntimeException {

    private static final String INITIAL_MESSAGE = "Negative value input, expected positive.";

    public NegativeValueException() {
        super(INITIAL_MESSAGE);
    }

    public NegativeValueException(String message) {
        super(message);
    }

    public NegativeValueException(int enteredSize) {
        this(INITIAL_MESSAGE + " Input: " + enteredSize);
    }
}
