package game.command.utilsCommands;

import game.characters.Player;
import game.command.Command;
import game.uiUtils.Colors;

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
