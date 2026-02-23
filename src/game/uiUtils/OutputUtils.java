package game.uiUtils;

import game.gameUtils.Coordinates;
import game.gameUtils.Room;

/**
 * This class is solving problems with printing in console
 *
 * @author Maksym Kulynych
 */
public class OutputUtils {
    /**
     * This method prints message in console
     * @param message text that must be printed
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * This method prints all room with objects in it into console
     * @param room room that must be printed
     */
    public void printRoom(Room room) {
        for (int i = room.getHeight() - 1; i >= 0; i--) {
            for (int j = 0; j < room.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(j, i);
                if (room.getGameObjects().containsKey(coordinates)) {
                    System.out.print(Colors.MAGENTA + "| " + room.getGameObjects().get(coordinates).getSprite() + " |" + Colors.RESET);
                }
                else {
                    System.out.print(Colors.MAGENTA + "|    |" + Colors.RESET);
                }
            }
            System.out.println();
        }
    }
}
