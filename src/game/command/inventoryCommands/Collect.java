package game.command.inventoryCommands;

import game.characters.Player;
import game.command.Command;
import game.items.Item;

public class Collect extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) return "Příkaz 'seber' nepotřebuje další argumenty";

        if (player.isObjectNearByType(Item.class, true, player.getCurrentRoom())) {
            Item nearItem = (Item) player.getObjectNearByType(Item.class, true, player.getCurrentRoom());

            boolean isCollected = player.getInventory().addItem(nearItem);

            if (!isCollected) return "Váš batoh je přeplněný";

            player.removeObjectNearByType(Item.class, true, player.getCurrentRoom());

            return "Sebrali jste předmět <" + nearItem.getName() + ">" + "\n" +
                    "Předmět umožňuje: " + nearItem.getDescription();
        }
        return "Nemáte vedle žádné předměty na sebrání";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
