package game.exceptions;

/**
 * This exception throws when attribute 'maxCount' < 0
 *
 * @author Maksym Kulynych
 */
public class BadMaxCountException extends RuntimeException {
    public BadMaxCountException() {
        super();
    }
}
