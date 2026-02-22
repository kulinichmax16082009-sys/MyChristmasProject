package game.commands.utilsCommands;

import game.characters.Player;
import game.commands.Command;
import game.uiUtils.Colors;

/**
 * This class represents 'konec' command, which is used to exit game
 *
 * @author Maksym Kulynych
 */
public class Quit extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        return Colors.BRIGHT_GREEN + "Program končí..." + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return true;
    }
}
