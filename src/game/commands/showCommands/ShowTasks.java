package game.commands.showCommands;

import game.characters.Player;
import game.commands.Command;
import game.uiUtils.Colors;

/**
 * This class represents 'ukoly' command, which is used to show all tasks, which player has
 *
 * @author Maksym Kulynych
 */
public class ShowTasks extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) {
            return Colors.BRIGHT_RED + "Příkaz 'úkoly' nepotřebuje další argumenty" + Colors.RESET;
        }
        if (player.getTasks().isEmpty()) {
            return Colors.BRIGHT_BLUE + "Zatím nemáte žádné úkoly" + Colors.RESET;
        }

        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < player.getTasks().size(); i++) {
            tasks.append(player.getTasks().get(i).toString()).append("\n").append("===========================").append("\n");
        }
        return Colors.BRIGHT_BLUE + tasks + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
