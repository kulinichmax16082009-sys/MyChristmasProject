package game.command.utilsCommands;

import game.characters.Player;
import game.command.Command;
import game.uiUtils.FileManager;

public class Hint extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        FileManager fileManager = new FileManager();
        String hint = player.getCurrentRoom().getName().toLowerCase().split(" ")[0];
        if (player.isTalking()) fileManager.readAllTxt("resources/txtFiles/hints/mluví");
        return fileManager.readAllTxt("resources/txtFiles/hints/" + hint);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
