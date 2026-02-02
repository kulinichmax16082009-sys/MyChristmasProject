package game.command.walkCommands;

import game.characters.Player;
import game.command.Command;
import game.gameUtils.Coordinates;

public class Walk extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (player.getIsTalking()) return "Příkaz nejde použít při dialogu";
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
            default: return "Nekorektní nebo nezadáný směr pohybu";
        }

        Coordinates where = new Coordinates(player.getCoordinates().getX() + shiftX, player.getCoordinates().getY() + shiftY);

        boolean isMoved = player.getCurrentRoom().move(where, player);

        if (isMoved) return result + commandArgument;
        return "Nedefinované souřadnice";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
