package game.command.showCommands;

import game.characters.Player;
import game.command.Command;
import game.uiUtils.Colors;

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
