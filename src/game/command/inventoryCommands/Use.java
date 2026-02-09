package game.command.inventoryCommands;

import game.characters.Player;
import game.command.Command;
import game.items.Item;
import game.uiUtils.Colors;
import game.uiUtils.RandomGenerator;

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
