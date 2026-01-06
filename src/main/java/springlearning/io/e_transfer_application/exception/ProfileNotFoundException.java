package springlearning.io.e_transfer_application.exception;

public class ProfileNotFoundException extends RuntimeException {

    public ProfileNotFoundException(String message){
        super(message);
    }

    public ProfileNotFoundException(Long userId){
        super("Profile not found for UserdId : "+ userId);
    }

    public ProfileNotFoundException(String identifier, boolean isReceiver) {
        super("Profile not found for identifier: " + identifier);
    }
}
