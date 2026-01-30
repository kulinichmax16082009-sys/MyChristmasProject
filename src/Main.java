import game.characters.Player;
import game.gameUtils.*;
import game.uiUtils.OutputUtils;
import game.uiUtils.RandomGenerator;

public class Main {
    public static void main(String[] args) {

        System.out.println(2%3);

        OutputUtils outputUtils = new OutputUtils();
        WorldGenerator worldGenerator = new WorldGenerator();
        RoomFactory roomFactory = new RoomFactory();
        Player player = new Player();
        player.setRoomsLeftCount(16);
//        Room roomA = new Room("a", 15, 15);
//
//      Door door = new Door();
//       roomA.place(new Coordinates(3,3), door);
//       roomA.place(new Coordinates(4,3), new Chair());
//
//       System.out.println(door.getAnyItemNear(false, roomA).getCoordinates());
//
//
//
//        Room roomB = new Room("b", 6, 6);
//
//        roomFactory.generateItems(roomA, new RandomGenerator());
//        roomFactory.generateItems(roomB, new RandomGenerator());
//
//        roomFactory.connectRooms(roomA, roomB, new RandomGenerator());
//
//        outputUtils.printRoom(roomA);
//        System.out.println();
//        outputUtils.printRoom(roomB);

//        Room newRoom = roomFactory.generateRoom(new RandomGenerator());
//        outputUtils.printRoom(newRoom);
        worldGenerator.initializeWorld(roomFactory, new RandomGenerator(), player);
        Room center = worldGenerator.initializeCenterRoom(player, new RandomGenerator(), roomFactory);
        worldGenerator.connectAllRooms(center, roomFactory, new RandomGenerator());

        outputUtils.printRoom(center);

        Game game = new Game();
        game.play();
    }
}