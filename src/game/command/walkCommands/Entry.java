package game.command.walkCommands;

import game.characters.Player;
import game.command.Command;

public class Entry extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
