package datastructure.exceptions;

public class DestroyedDataStructureException extends RuntimeException {

    public DestroyedDataStructureException() {
        super("Cannot perform operation. Data structure is destroyed.");
    }

    public DestroyedDataStructureException(String message) {
        super(message);
    }
}
