import game.characters.Player;
import game.commands.inventoryCommands.Drop;
import game.items.keepable.MagicPear;
import game.uiUtils.Colors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class for testing the functionality of dropping items from the player's inventory.
 *
 * @author Maksym Kulynych
 */
class DropCommandTest {

    @Test
    void itemDropIfPlayerHas() {
        MagicPear magicPear = new MagicPear();
        magicPear.setName("kouzelná_hruška");

        Player player = new Player();
        player.getInventory().addItem(magicPear);

        Drop drop = new Drop();

        String result = drop.execute(player, "kouzelná_hruška");

        assertEquals(Colors.BRIGHT_YELLOW + "Předmět <kouzelná_hruška> byl úspěšně vyhozen" + Colors.RESET, result);
    }

    @Test
    void itemNotDropIfPlayerHasNot() {
        Player player = new Player();

        Drop drop = new Drop();

        String result = drop.execute(player, "kouzelná_hruška");

        assertEquals(Colors.BRIGHT_RED + "Špatný format zadání předmětu nebo předmět neexistuje" + Colors.RESET, result);
    }
}