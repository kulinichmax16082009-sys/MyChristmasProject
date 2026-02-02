package game.gameUtils;

import game.characters.Player;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;

public class WorldGenerator {
    private static final int MIN_MAIN_CLASS_SIZE = 5;
    private static final int MAX_MAIN_CLASS_SIZE = 10;
    private Room hall;
    private Room mainClass;
    private ArrayList<Room> rooms;

    public WorldGenerator() {
        this.rooms = new ArrayList<>();
    }

    public void initializeWorld(RoomFactory roomFactory, RandomGenerator rnd, Player player) {
        for (int i = 0; i < player.getRoomsLeftCount(); i++) rooms.add(roomFactory.generateRoom(rnd));
    }

    public void connectAllRooms(RoomFactory roomFactory, RandomGenerator rnd) {
        roomFactory.connectRooms(hall, mainClass, rnd);
        for (Room room : rooms) roomFactory.connectRooms(hall, room, rnd);
        for (Room room : rooms) roomFactory.clearWayFromHallDoorToTeacher(room, hall);
    }

    public void initializeHall(Player player, RandomGenerator rnd) {
        String name = "Chodba";
        int width = player.getRoomsLeftCount() * 2;
        int heigh = rnd.randomNumber(5,7);

        hall = new Room(name, width, heigh);
    }

    public void initializeMainClass(RandomGenerator rnd) {
        String name = "Učebna č.1";
        int width = rnd.randomNumber(MIN_MAIN_CLASS_SIZE, MAX_MAIN_CLASS_SIZE);
        int heigh = rnd.randomNumber(MIN_MAIN_CLASS_SIZE, MAX_MAIN_CLASS_SIZE);

        mainClass = new Room(name, width, heigh);
    }

    public Room getHall() {
        return hall;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Room getMainClass() {
        return mainClass;
    }
}