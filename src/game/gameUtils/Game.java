package game.gameUtils;

import game.characters.Player;
import game.uiUtils.OutputUtils;
import game.uiUtils.RandomGenerator;
import game.uiUtils.ScannerUtils;

public class Game {
    public void play() {
        WorldGenerator worldGenerator = new WorldGenerator();
        RoomFactory roomFactory = new RoomFactory();
        ScannerUtils scannerUtils = new ScannerUtils();
        OutputUtils outputUtils = new OutputUtils();
        RandomGenerator rnd = new RandomGenerator();

        Player player = new Player();

        //Pokud nezadame, celý system spadne
        player.setRoomsLeftCount(8);
        scannerUtils.initialize();

        worldGenerator.initializeWorld(roomFactory, rnd, player);
        Room center = worldGenerator.initializeCenterRoom(player, rnd, roomFactory);
        worldGenerator.connectAllRooms(center, roomFactory, rnd);

        player.setCurrentRoom(center);
        center.place(center.findFreeCoordinates().get(0), player);

        player.setOneStepDistance(1);

        while (true) {
            outputUtils.printRoom(player.getCurrentRoom());
            scannerUtils.complete(player, outputUtils);
        }
    }
}