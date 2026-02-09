package game.command.inventoryCommands;

import game.characters.Player;
import game.command.Command;
import game.items.Item;
import game.uiUtils.Colors;

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
