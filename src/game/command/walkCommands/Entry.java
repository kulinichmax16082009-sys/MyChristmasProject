package game.command.walkCommands;

import game.characters.Player;
import game.command.Command;
import game.items.unkeepable.Door;
import game.uiUtils.RandomGenerator;

public class Entry extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) return "Příkaz 'vstup' nepotřebuje další argumenty";

        if (player.isObjectNearByType(Door.class, false, player.getCurrentRoom())) {
            Door nearDoor = (Door) player.getObjectNearByType(Door.class, false, player.getCurrentRoom());

            if (nearDoor.getIsOpen()) {

                nearDoor.useAbility(player, new RandomGenerator());
                return "Vstoupili jste do místnosti <" + nearDoor.getNextRoom().getName() + ">";

            } else return "Dveře do místnosti <" + nearDoor.getNextRoom().getName() + "> jsou zamčeny";
        }
        return "Vedle vás nejsou žádné dveře pro vstup";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
