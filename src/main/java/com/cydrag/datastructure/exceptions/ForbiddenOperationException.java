package com.cydrag.datastructure.exceptions;

public class ForbiddenOperationException extends RuntimeException {

    public ForbiddenOperationException() {
        super("This operation is not allowed.");
    }

    public ForbiddenOperationException(String message) {
        super(message);
    }
}
