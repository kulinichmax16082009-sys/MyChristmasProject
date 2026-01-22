package game.command.talkCommands;

import game.command.Command;

public class StopTalking extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
