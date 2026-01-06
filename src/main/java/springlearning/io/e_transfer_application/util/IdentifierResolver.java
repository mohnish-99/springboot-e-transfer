package springlearning.io.e_transfer_application.util;

import springlearning.io.e_transfer_application.exception.InvalidIdentifierException;

import java.util.regex.Pattern;

public class IdentifierResolver {
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE = Pattern.compile("^\\+?\\d{10,15}$");

    public static IdentifierType identifierType(String identifier) throws InvalidIdentifierException {

        if(EMAIL.matcher(identifier).matches()){
            return IdentifierType.EMAIL;
        } else if (PHONE.matcher(identifier).matches()) {
            return IdentifierType.PHONE;
        }
        throw new InvalidIdentifierException(identifier);
    }

}


