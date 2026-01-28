import game.characters.teachers.ItTeacher;
import game.gameUtils.Coordinates;
import game.gameUtils.Game;
import game.gameUtils.Room;
import game.gameUtils.RoomFactory;
import game.uiUtils.OutputUtils;
import game.uiUtils.RandomGenerator;

public class Main {
    public static void main(String[] args) {
//        OutputUtils outputUtils = new OutputUtils();
//        RoomFactory roomFactory = new RoomFactory();
//        Room roomA = new Room("d", 10, 4);
//        Room roomB = new Room("b", 6, 6);
//        roomFactory.generateItems(roomA, new RandomGenerator());
//        roomFactory.generateItems(roomB, new RandomGenerator());
//
//
//        roomFactory.connectRooms(roomA, roomB, new RandomGenerator());
//
//        System.out.println("Connected Rooms:");
//        outputUtils.printRoom(roomA);
//        System.out.println();
//        outputUtils.printRoom(roomB);
//        System.out.println();

//        Room room = roomFactory.generateRoom(new RandomGenerator());
//        outputUtils.printRoom(room);
//        System.out.println(room.getName());
//
//        roomFactory.connectRooms(roomA, roomB, new RandomGenerator());
//        roomA.place(new Coordinates(roomA.getWidth() - 1,0), new ItTeacher(""));
//        roomB.place(new Coordinates(roomB.getWidth() - 1,0), new ItTeacher(""));
//
//
//        outputUtils.printRoom(roomA);
//        System.out.println();
//        outputUtils.printRoom(roomB);
//        roomFactory.generateItems(roomA, new RandomGenerator());
//        roomFactory.generateItems(roomB, new RandomGenerator());
//        System.out.println("After generating items:");
//        outputUtils.printRoom(roomA);
//        System.out.println();
//        outputUtils.printRoom(roomB);
//
//        roomFactory.clearWayFromDoorToTeacher(roomB);
//        System.out.println("After clearing way in room B:");
//        outputUtils.printRoom(roomB);

        Game game = new Game();
        game.play();
    }
}