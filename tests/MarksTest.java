import game.characters.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for testing the functionality of the Marks class,
 * specifically checking if the player has enough ones to end the game.
 *
 * @author Maksym Kulynych
 */
class MarksTest {

    @Test
    void playerHasEnoughOnesToEnd() {
        Player player = new Player();
        player.setRequiredOnesAmount(5);

        for (int i = 0; i < player.getRequiredOnesAmount(); i++) {
            player.getMarks().addMark(1);
        }

        assertTrue(player.getMarks().hasEnoughOnes(player));
    }
}