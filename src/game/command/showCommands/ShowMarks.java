package game.command.showCommands;

import game.characters.Player;
import game.command.Command;

public class ShowMarks extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) return "Příkaz 'známky' nepotřebuje další argumenty";
        return player.getMarks().toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
