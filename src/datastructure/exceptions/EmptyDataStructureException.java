package datastructure.exceptions;

public class EmptyDataStructureException extends RuntimeException {

    private static final String INITIAL_MESSAGE = "Data structure is empty.";

    public EmptyDataStructureException() {
        this(INITIAL_MESSAGE);
    }

    public EmptyDataStructureException(String message) {
        super(message + INITIAL_MESSAGE);
    }
}
