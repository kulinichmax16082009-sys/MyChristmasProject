package game.command.showCommands;

import game.characters.Player;
import game.command.Command;

public class ShowTasks extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) return "Příkaz 'úkoly' nepotřebuje další argumenty";
        if (player.getTasks().isEmpty()) return "Zatím nemáte žádné úkoly";

        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < player.getTasks().size(); i++) {
            tasks.append(player.getTasks().get(i).toString()).append("\n").append("==================").append("\n");
        }
        return tasks.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
