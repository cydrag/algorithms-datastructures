package datastructure.exceptions;

public class NullValueException extends RuntimeException {

    public NullValueException() {
        super("Operation cannot be performed with null value.");
    }

    public NullValueException(String message) {
        super(message);
    }
}
