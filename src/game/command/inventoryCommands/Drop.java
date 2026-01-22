package game.command.inventoryCommands;

import game.command.Command;

public class Drop extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
