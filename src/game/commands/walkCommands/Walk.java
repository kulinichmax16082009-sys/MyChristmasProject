package game.commands.walkCommands;

import game.characters.Player;
import game.commands.Command;
import game.gameUtils.Coordinates;
import game.uiUtils.Colors;

/**
 * This class represents 'jdi' command, which is used to move player in the room
 *
 * @author Maksym Kulynych
 */
public class Walk extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (player.getIsTalking()) {
            return Colors.BRIGHT_RED + "Příkaz nejde použít při dialogu" + Colors.RESET;
        }
        int shiftX = 0, shiftY = 0;
        String result = "Hráč byl posunut na ";

        switch (commandArgument) {
            case "východ": {
                shiftX = player.getOneStepDistance();
                break;
            }
            case "západ": {
                shiftX = -1 * player.getOneStepDistance();
                break;
            }
            case "jih": {
                shiftY = -1 * player.getOneStepDistance();
                break;
            }
            case "sever": {
                shiftY = player.getOneStepDistance();
                break;
            }
            default: return Colors.BRIGHT_RED + "Nekorektní nebo nezadáný směr pohybu" + Colors.RESET;
        }

        Coordinates where = new Coordinates(player.getCoordinates().getX() + shiftX, player.getCoordinates().getY() + shiftY);

        boolean isMoved = player.getCurrentRoom().move(where, player);

        if (isMoved) return Colors.BRIGHT_YELLOW + result + commandArgument + Colors.RESET;
        return Colors.BRIGHT_RED + "Nedefinované souřadnice" + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
