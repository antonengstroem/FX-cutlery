package FXcutlery.exceptionhandling;

public class InvalidIsoCodeException extends RuntimeException {
    public InvalidIsoCodeException(String message) {
        super(message);
    }
}
