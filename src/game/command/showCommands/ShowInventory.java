package game.command.showCommands;

import game.characters.Player;
import game.command.Command;

public class ShowInventory extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) return "Příkaz 'inventář' nepotřebuje další argumenty";
        return player.getInventory().toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
