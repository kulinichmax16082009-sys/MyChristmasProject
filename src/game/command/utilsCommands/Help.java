package game.command.utilsCommands;

import game.characters.Player;
import game.command.Command;
import game.uiUtils.FileManager;

public class Help extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) return "Příkaz 'pomoc' nepotřebuje další argumenty";
        FileManager fileManager = new FileManager();
        return fileManager.readAllTxt("resources/txtFiles/commands");
    }

    @Override
    public boolean exit() {
        return false;
    }
}
