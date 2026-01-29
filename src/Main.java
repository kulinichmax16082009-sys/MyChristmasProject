import game.gameUtils.Coordinates;
import game.gameUtils.Game;
import game.gameUtils.Room;
import game.gameUtils.RoomFactory;
import game.items.unkeepable.Chair;
import game.items.unkeepable.Door;
import game.uiUtils.OutputUtils;
import game.uiUtils.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        OutputUtils outputUtils = new OutputUtils();
        RoomFactory roomFactory = new RoomFactory();
        Room roomA = new Room("a", 15, 15);

//        Door door = new Door();
//        roomA.place(new Coordinates(3,3), door);
//        roomA.place(new Coordinates(4,3), new Chair());
//
//        System.out.println(door.getAnyItemNear(false, roomA).getCoordinates());



        Room roomB = new Room("b", 6, 6);

        roomFactory.generateItems(roomA, new RandomGenerator());
        roomFactory.generateItems(roomB, new RandomGenerator());

        roomFactory.connectRooms(roomA, roomB, new RandomGenerator());

        outputUtils.printRoom(roomA);
        System.out.println();
        outputUtils.printRoom(roomB);


        Game game = new Game();
        game.play();
    }
}