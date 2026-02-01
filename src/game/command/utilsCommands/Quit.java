package game.command.utilsCommands;

import game.characters.Player;
import game.command.Command;

public class Quit extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        return "Program končí...";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
