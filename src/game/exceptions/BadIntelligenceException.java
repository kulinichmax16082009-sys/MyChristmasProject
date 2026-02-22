package game.exceptions;

/**
 * This exception throws when attribute 'intelligence' <= 0
 *
 * @author Maksym Kulynych
 */
public class BadIntelligenceException extends RuntimeException {
    public BadIntelligenceException() {
        super();
    }
}
