package game.gameUtils;

import game.characters.Player;
import game.uiUtils.OutputUtils;
import game.uiUtils.RandomGenerator;
import game.uiUtils.ScannerUtils;

public class Game {
    public void play() {
        RoomFactory roomFactory = new RoomFactory();
        ScannerUtils scannerUtils = new ScannerUtils();
        OutputUtils outputUtils = new OutputUtils();
        Room newRoom = roomFactory.generateRoom(new RandomGenerator());
        Player player = new Player();
        newRoom.place(new Coordinates(0,0), player);
        player.setCurrentRoom(newRoom);

        scannerUtils.initialize();

        player.setOneStepDistance(1);

        while (true) {
            outputUtils.printRoom(player.getCurrentRoom());
            scannerUtils.complete(player, outputUtils);
        }
    }
}
