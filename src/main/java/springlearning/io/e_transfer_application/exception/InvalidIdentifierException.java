package springlearning.io.e_transfer_application.exception;

public class InvalidIdentifierException extends RuntimeException {
    public InvalidIdentifierException(String identifier) {
        super(identifier);
    }
}
