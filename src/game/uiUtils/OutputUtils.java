package game.uiUtils;

import game.gameUtils.Coordinates;
import game.gameUtils.Room;

public class OutputUtils {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printRoom(Room room) {
        for (int i = room.getHeight() - 1; i >= 0; i--) {
            for (int j = 0; j < room.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(j, i);
                if (room.getGameObjects().containsKey(coordinates)) System.out.print("| " + room.getGameObjects().get(coordinates).getSprite() + " |");
                else System.out.print("|    |");
            }
            System.out.println();
        }
    }

}
