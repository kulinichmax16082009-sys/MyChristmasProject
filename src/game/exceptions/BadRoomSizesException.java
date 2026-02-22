package game.exceptions;

/**
 * This exception throws when room size attribute <= 0 or can't be
 * generated, because of the bad random range
 */
public class BadRoomSizesException extends RuntimeException {
    public BadRoomSizesException() {
        super();
    }
}
