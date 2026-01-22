package game.command.talkCommands;

import game.command.Command;

public class Talk extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
