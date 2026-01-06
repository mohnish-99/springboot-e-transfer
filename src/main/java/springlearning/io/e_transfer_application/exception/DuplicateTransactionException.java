package springlearning.io.e_transfer_application.exception;

public class DuplicateTransactionException extends Throwable {
    public DuplicateTransactionException(String transactionId) {
        super(transactionId);
    }
}
