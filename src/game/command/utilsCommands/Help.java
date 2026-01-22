package game.command.utilsCommands;

import game.characters.Player;
import game.command.Command;

public class Help extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
