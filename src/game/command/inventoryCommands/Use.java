package game.command.inventoryCommands;

import game.characters.Player;
import game.command.Command;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class Use extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        for (int i = 0; i < player.getInventory().getItems().size(); i++) {
            if (player.getInventory().getItems().get(i).getName().equals(commandArgument.trim().toLowerCase())) {
                Item used = player.getInventory().getItems().get(i);

                used.useAbility(player, new RandomGenerator());

                player.getInventory().removeItem(used);

                return "Předmět <" + used.getName() + "> byl úspěšně použit";
            }
        }
        return "Špatný format zadání předmětu nebo předmět neexistuje";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
