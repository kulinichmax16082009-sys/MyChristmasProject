package game.command.showCommands;

import game.characters.Player;
import game.command.Command;
import game.uiUtils.Colors;

public class ShowMarks extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) {
            return Colors.BRIGHT_RED + "Příkaz 'známky' nepotřebuje další argumenty" + Colors.RESET;
        }
        return Colors.BRIGHT_BLUE + player.getMarks().toString() + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
