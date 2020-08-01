package pl.degath.random.infrastructure;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
