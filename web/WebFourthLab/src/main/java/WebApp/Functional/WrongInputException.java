package WebApp.Functional;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String message) {
        super(message);
    }
}