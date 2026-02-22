package game.exceptions;

/**
 * This exception throws when attribute 'requiredOnesAmount' <= 0
 *
 * @author Maksym Kulynych
 */
public class BadRequiredOnesAmountException extends RuntimeException {
    public BadRequiredOnesAmountException() {
        super();
    }
}
