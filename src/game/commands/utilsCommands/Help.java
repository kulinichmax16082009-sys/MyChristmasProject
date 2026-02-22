package game.commands.utilsCommands;

import game.characters.Player;
import game.commands.Command;
import game.uiUtils.Colors;
import game.uiUtils.FileManager;

/**
 * This class represents 'pomoc' command, which is used to get list of all commands in game
 *
 * @author Maksym Kulynych
 */
public class Help extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) {
            return Colors.BRIGHT_RED + "Příkaz 'pomoc' nepotřebuje další argumenty" + Colors.RESET;
        }
        FileManager fileManager = new FileManager();
        return Colors.BRIGHT_YELLOW + fileManager.readAllTxt("resources/txtFiles/commands") + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
