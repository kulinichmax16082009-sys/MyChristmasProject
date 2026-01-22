package game.command.utilsCommands;

import game.command.Command;

public class Hint extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
