package in.UserDetails;

public class CustomException extends Exception {

    public CustomException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
    
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}
