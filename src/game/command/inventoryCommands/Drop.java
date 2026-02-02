package game.command.inventoryCommands;

import game.characters.Player;
import game.command.Command;
import game.items.Item;

public class Drop extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        for (int i = 0; i < player.getInventory().getItems().size(); i++) {

            if (player.getInventory().getItems().get(i).getName().equals(commandArgument)) {

                Item deleted = player.getInventory().getItems().get(i);
                player.getInventory().getItems().remove(i);

                return "Předmět <" + deleted.getName() + "> byl úspěšně vyhozen";
            }
        }
        return "Špatný format zadání předmětu nebo předmět neexistuje";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
