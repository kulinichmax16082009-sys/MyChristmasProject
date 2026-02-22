import game.characters.Player;
import game.commands.showCommands.ShowIntelligence;
import game.uiUtils.Colors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for testing the functionality of the ShowIntelligence command,
 * specifically checking if the player's intelligence is displayed
 *
 * @author Maksym Kulynych
 */
class ShowIntelligenceTest {

    @Test
    void intelligenceWillShowIfNoArguments() {
        Player player = new Player();
        player.setIntelligence(1000);

        ShowIntelligence showIntelligence = new ShowIntelligence();

        String result = showIntelligence.execute(player, "");

        assertEquals(Colors.BRIGHT_BLUE + "Váše inteligence: 1000" + Colors.RESET, result);
    }

    @Test
    void intelligenceWillNotShowIfArguments() {
        Player player = new Player();
        player.setIntelligence(1000);

        ShowIntelligence showIntelligence = new ShowIntelligence();

        String result = showIntelligence.execute(player, "argument");

        assertEquals(Colors.BRIGHT_RED + "Příkaz 'inteligence' nepotřebuje další argumenty" + Colors.RESET, result);
    }
}