package game.commands.inventoryCommands;

import game.characters.Player;
import game.commands.Command;
import game.items.Item;
import game.uiUtils.Colors;

/**
 * This class represents 'vyhod' command, which is used to drop item from inventory
 *
 * @author Maksym Kulynych
 */
public class Drop extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        for (int i = 0; i < player.getInventory().getItems().size(); i++) {

            if (player.getInventory().getItems().get(i).getName().equals(commandArgument)) {

                Item deleted = player.getInventory().getItems().get(i);
                player.getInventory().getItems().remove(i);

                return Colors.BRIGHT_YELLOW + "Předmět <" + deleted.getName() + "> byl úspěšně vyhozen" + Colors.RESET;
            }
        }
        return Colors.BRIGHT_RED + "Špatný format zadání předmětu nebo předmět neexistuje" + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
