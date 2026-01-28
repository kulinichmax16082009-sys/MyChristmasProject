package game.uiUtils;

import game.characters.Player;
import game.gameUtils.Coordinates;

public class OutputUtils {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printCurrentRoom(Player player) {
        for (int i = player.getCurrentRoom().getHeight() - 1; i >= 0; i--) {
            for (int j = 0; j < player.getCurrentRoom().getWidth(); j++) {
                Coordinates coordinates = new Coordinates(j, i);
                if (player.getCurrentRoom().getGameObjects().containsKey(coordinates)) System.out.print("| " + player.getCurrentRoom().getGameObjects().get(coordinates).getSprite() + " |");
                else System.out.print("|    |");
            }
            System.out.println();
        }
    }

}
