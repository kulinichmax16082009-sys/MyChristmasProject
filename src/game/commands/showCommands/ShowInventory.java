package game.commands.showCommands;

import game.characters.Player;
import game.commands.Command;
import game.uiUtils.Colors;

/**
 * This class represents 'inventar' command, which is used to show all items, which player has in inventory
 *
 * @author Maksym Kulynych
 */
public class ShowInventory extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) {
            return Colors.BRIGHT_RED + "Příkaz 'inventář' nepotřebuje další argumenty" + Colors.RESET;
        }
        return Colors.BRIGHT_BLUE + player.getInventory().toString() + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
