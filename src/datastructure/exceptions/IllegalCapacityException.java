package datastructure.exceptions;

public class IllegalCapacityException extends RuntimeException {

    public IllegalCapacityException() {
        this("Illegal capacity size. Capacity must be greater than 0.");
    }

    public IllegalCapacityException(String message) {
        super(message);
    }
}
