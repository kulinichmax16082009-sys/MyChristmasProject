package game.commands.inventoryCommands;

import game.characters.Player;
import game.commands.Command;
import game.items.Item;
import game.uiUtils.Colors;
import game.uiUtils.RandomGenerator;

/**
 * This class represents 'pouzit' command, which is used to use item from inventory
 *
 * @author Maksym Kulynych
 */
public class Use extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        for (int i = 0; i < player.getInventory().getItems().size(); i++) {
            if (player.getInventory().getItems().get(i).getName().equals(commandArgument)) {
                Item used = player.getInventory().getItems().get(i);

                used.useAbility(player, new RandomGenerator());

                player.getInventory().removeItem(used);

                return Colors.BRIGHT_YELLOW + "Předmět <" + used.getName() + "> byl úspěšně použit" + Colors.RESET;
            }
        }
        return Colors.BRIGHT_RED + "Špatný format zadání předmětu nebo předmět neexistuje" + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
