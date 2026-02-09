package game.command.walkCommands;

import game.characters.Player;
import game.command.Command;
import game.items.unkeepable.Door;
import game.uiUtils.Colors;
import game.uiUtils.RandomGenerator;

public class Entry extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (player.getIsTalking()) return Colors.BRIGHT_RED + "Příkaz nejde použít při dialogu" + Colors.RESET;
        if (commandArgument != null && !commandArgument.isEmpty()) {
            return Colors.BRIGHT_RED + "Příkaz 'vstup' nepotřebuje další argumenty" + Colors.RESET;
        }

        if (player.isObjectNearByType(Door.class, false, player.getCurrentRoom())) {
            Door nearDoor = (Door) player.getObjectNearByType(Door.class, false, player.getCurrentRoom());

            if (nearDoor.getIsOpen()) {

                nearDoor.useAbility(player, new RandomGenerator());
                return Colors.BRIGHT_YELLOW + "Vstoupili jste do místnosti <" + nearDoor.getNextRoom().getName() + ">" + Colors.RESET;

            } else return Colors.BRIGHT_YELLOW + "Dveře do místnosti <" + nearDoor.getNextRoom().getName() + "> jsou zamčeny" + Colors.RESET;
        }
        return Colors.BRIGHT_RED + "Vedle vás nejsou žádné dveře pro vstup" + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
