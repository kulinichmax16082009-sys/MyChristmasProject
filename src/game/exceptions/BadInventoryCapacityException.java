package game.exceptions;

/**
 * This exception throws when attribute 'capacity' < 0
 *
 * @author Maksym Kulynych
 */
public class BadInventoryCapacityException extends RuntimeException {
    public BadInventoryCapacityException() {
        super();
    }
}
