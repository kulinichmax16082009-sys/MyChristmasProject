package game.command.showCommands;

import game.characters.Player;
import game.command.Command;
import game.uiUtils.Colors;

public class ShowIntelligence extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()){
            return Colors.BRIGHT_RED + "Příkaz 'inteligence' nepotřebuje další argumenty" + Colors.RESET;
        }
        return Colors.BRIGHT_BLUE + "Váše inteligence: " + player.getIntelligence() + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
