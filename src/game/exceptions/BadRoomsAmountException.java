package game.exceptions;

/**
 * This exception throws when attribute 'requiredOnesAmount' <= 0 or can't be
 * generated, because of the bad random range
 *
 * @author Maksym Kulynych
 */
public class BadRoomsAmountException extends RuntimeException {
    public BadRoomsAmountException() {
        super();
    }
}
