package game.gameUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.teachers.Teacher;
import game.exceptions.BadRoomsAmountException;
import game.exceptions.BadWorldCharacteristicsFormatException;
import game.items.unkeepable.Door;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * This class is used to generate main game world (hall, main class, other rooms)
 *
 * @author Maksym Kulynych
 */
public class WorldGenerator {
    private static final int MIN_MAIN_CLASS_SIZE = 5;
    private static final int MAX_MAIN_CLASS_SIZE = 10;
    private int minRoomsAmount;
    private int maxRoomsAmount;
    private int roomsAmount;
    private Room hall;
    private Room mainClass;
    private ArrayList<Room> rooms;

    public WorldGenerator() {
        this.rooms = new ArrayList<>();
        minRoomsAmount = 1;
        maxRoomsAmount = 5;
    }

    /**
     * This method fills up rooms list with randomly generated rooms
     * @param roomFactory is used to generate new room
     * @param rnd is used to make rooms generation random
     */
    private void initializeRooms(RoomFactory roomFactory, RandomGenerator rnd) {
        for (int i = 0; i < roomsAmount; i++) rooms.add(roomFactory.generateRoom(rnd));
    }

    /**
     * This method connects all rooms together (hall, main class and other rooms)
     * @param roomFactory is used to connect two rooms together and to clear way from hall door to the teacher
     * @param rnd is used to generate doors in rooms randomly while connecting
     */
    private void connectAllRooms(RoomFactory roomFactory, RandomGenerator rnd) {
        roomFactory.connectRooms(hall, mainClass, rnd);
        for (Room room : rooms) roomFactory.connectRooms(hall, room, rnd);
        for (Room room : rooms) roomFactory.clearWayFromHallDoorToTeacher(room);
    }

    /**
     * This method initializes main hall by picking random width and heigh
     * @param rnd is used to generate random size number
     */
    private void initializeHall(RandomGenerator rnd) {
        String name = "Chodba";
        int width = roomsAmount * 2;
        int heigh = rnd.randomNumber(5,7);

        hall = new Room(name, width, heigh, RoomType.HALL);
    }

    /**
     * This method initializes main class by picking random width and heigh
     * @param rnd is used to generate random size number
     */
    private void initializeMainClass(RandomGenerator rnd) {
        String name = "Učebna č.1";
        int width = rnd.randomNumber(MIN_MAIN_CLASS_SIZE, MAX_MAIN_CLASS_SIZE);
        int heigh = rnd.randomNumber(MIN_MAIN_CLASS_SIZE, MAX_MAIN_CLASS_SIZE);

        mainClass = new Room(name, width, heigh, RoomType.MAIN_CLASS);
    }

    /**
     * This method initializes new world by initializing hall, main class, rooms and connecting them together
     * @param roomFactory is used to initialize rooms and connect them
     * @param rnd is used to generate random rooms amount in game world in range from
     * minRoomsAmount to maxRoomsAmount, which is taken from .json file
     */
    public void initializeWorld(RoomFactory roomFactory, RandomGenerator rnd) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream("resources/jsonFiles/worldAndRooms/worldGenerator.json")) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            throw new BadWorldCharacteristicsFormatException();
        }

        if (minRoomsAmount <= 0 || maxRoomsAmount <= 0 || maxRoomsAmount <= minRoomsAmount) {
            throw new BadRoomsAmountException();
        }

        roomsAmount = rnd.randomNumber(minRoomsAmount, maxRoomsAmount);

        initializeRooms(roomFactory, rnd);
        initializeMainClass(rnd);
        initializeHall(rnd);
        connectAllRooms(roomFactory, rnd);
    }

    public void openMainClass() {
        for (GameObject gameObject : mainClass.getGameObjects().values()) {
            if (gameObject instanceof Door door) {
                door.setConnectedDoorsOpen(true);
            }
        }
    }

    /**
     * This method checks if there is any teacher left in room of rooms list
     * @return true - there is at least 1 teacher in any room, false - there is no teacher left
     */
    public boolean isAnyTeacherLeft() {
        for (Room room : rooms) {
            for (GameObject gameObject : room.getGameObjects().values()) {
                if (gameObject instanceof Teacher) return true;
            }
        }
        return false;
    }

    public int getMaxRoomsAmount() {
        return maxRoomsAmount;
    }

    public int getMinRoomsAmount() {
        return minRoomsAmount;
    }

    public Room getHall() {
        return hall;
    }
}