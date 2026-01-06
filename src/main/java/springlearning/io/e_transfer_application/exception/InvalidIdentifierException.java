package springlearning.io.e_transfer_application.exception;

public class InvalidIdentifierException extends Throwable {
    public InvalidIdentifierException(String identifier) {
        super(identifier);
    }
}
