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

//      Pokud nezadame, celý system spadne
        player.setRoomsLeftCount(8);
        scannerUtils.initialize();

        worldGenerator.initializeWorld(roomFactory, rnd, player);
        worldGenerator.initializeMainClass(rnd);
        worldGenerator.initializeHall(player, rnd);
        worldGenerator.connectAllRooms(roomFactory, rnd);

        player.setCurrentRoom(worldGenerator.getHall());
        worldGenerator.getHall().place(worldGenerator.getHall().findFreeCoordinates().get(0), player);

        outputUtils.printRoom(worldGenerator.getMainClass());

        player.setOneStepDistance(1);

        System.out.println();
        System.out.println();

        player.initializePlayer();
        System.out.println(player);

        for (int i = 0; i < worldGenerator.getRooms().size(); i++) {
            outputUtils.printRoom(worldGenerator.getRooms().get(i));
            System.out.println();
        }


//        Room newRoom = roomFactory.generateRoom(rnd);
//        worldGenerator.setHall(newRoom);
//        outputUtils.printRoom(newRoom);
//        System.out.println();
//        Room roomA = roomFactory.generateRoom(rnd);
//        outputUtils.printRoom(roomA);
//
//        System.out.println();
//        System.out.println();
//
//        roomFactory.connectRooms(roomA, newRoom, rnd);
//        outputUtils.printRoom(newRoom);
//        System.out.println();
//        outputUtils.printRoom(roomA);
//
//        System.out.println();
//        System.out.println();
//
//        roomFactory.clearWayFromHallDoorToTeacher(roomA, newRoom);
//
//        outputUtils.printRoom(newRoom);
//        System.out.println();
//        outputUtils.printRoom(roomA);

        while (true) {
            outputUtils.printRoom(player.getCurrentRoom());
            scannerUtils.complete(player, outputUtils);
        }
    }
}