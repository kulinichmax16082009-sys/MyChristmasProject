package game.command.showCommands;

import game.characters.Player;
import game.command.Command;

public class ShowIntelligence extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) return "Příkaz 'inteligence' nepotřebuje další argumenty";
        return "Váše inteligence: " + player.getIntelligence();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
