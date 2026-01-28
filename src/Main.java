import game.gameUtils.Game;
import game.gameUtils.Room;
import game.gameUtils.RoomFactory;
import game.uiUtils.OutputUtils;
import game.uiUtils.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        OutputUtils outputUtils = new OutputUtils();
        RoomFactory roomFactory = new RoomFactory();
//        Room roomA = new Room("d", 10, 4);
//        Room roomB = new Room("b", 6, 6);

//        roomFactory.connectRooms(roomA, roomB);
//
//        System.out.println("Room A:");
//        System.out.println(roomA.getGameObjects().get(new Coordinates(0,0)));

        Room room = roomFactory.generateRoom(new RandomGenerator());
        outputUtils.printRoom(room);
        System.out.println(room.getName());

        Game game = new Game();
        game.play();
    }
}