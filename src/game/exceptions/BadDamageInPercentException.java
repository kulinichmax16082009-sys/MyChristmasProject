package game.exceptions;

/**
 * This exception throws when attribute 'damageInPercent' < 0
 *
 * @author Maksym Kulynych
 */
public class BadDamageInPercentException extends RuntimeException {
    public BadDamageInPercentException() {
        super();
    }
}
