package game.command.inventoryCommands;

import game.characters.Player;
import game.command.Command;
import game.items.Item;
import game.uiUtils.Colors;

public class Collect extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (player.getIsTalking()) return Colors.BRIGHT_RED + "Příkaz nejde použít při dialogu" + Colors.RESET;
        if (commandArgument != null && !commandArgument.isEmpty()) {
            return Colors.BRIGHT_RED + "Příkaz 'seber' nepotřebuje další argumenty" + Colors.RESET;
        }

        if (player.isObjectNearByType(Item.class, true, player.getCurrentRoom())) {
            Item nearItem = (Item) player.getObjectNearByType(Item.class, true, player.getCurrentRoom());

            boolean isCollected = player.getInventory().addItem(nearItem);

            if (!isCollected) return Colors.BRIGHT_RED + "Váš batoh je přeplněný" + Colors.RESET;

            player.removeObjectNearByType(Item.class, true, player.getCurrentRoom());

            return Colors.BRIGHT_YELLOW + "Sebrali jste předmět <" + nearItem.getName() + ">" + "\n" +
                    "Předmět umožňuje: " + nearItem.getDescription() + Colors.RESET;
        }
        return Colors.BRIGHT_RED + "Nemáte vedle žádné předměty na sebrání" + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
