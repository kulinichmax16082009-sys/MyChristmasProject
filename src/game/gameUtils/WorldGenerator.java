package game.gameUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.teachers.Teacher;
import game.exceptions.BadRoomsAmountException;
import game.items.unkeepable.Door;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

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
    }

    private void initializeRooms(RoomFactory roomFactory, RandomGenerator rnd) {
        for (int i = 0; i < roomsAmount; i++) rooms.add(roomFactory.generateRoom(rnd));
    }

    private void connectAllRooms(RoomFactory roomFactory, RandomGenerator rnd) {
        roomFactory.connectRooms(hall, mainClass, rnd);
        for (Room room : rooms) roomFactory.connectRooms(hall, room, rnd);
        for (Room room : rooms) roomFactory.clearWayFromHallDoorToTeacher(room, hall);
    }

    private void initializeHall(RandomGenerator rnd) {
        String name = "Chodba";
        int width = roomsAmount * 2;
        int heigh = rnd.randomNumber(5,7);

        hall = new Room(name, width, heigh, RoomType.HALL);
    }

    private void initializeMainClass(RandomGenerator rnd) {
        String name = "Učebna č.1";
        int width = rnd.randomNumber(MIN_MAIN_CLASS_SIZE, MAX_MAIN_CLASS_SIZE);
        int heigh = rnd.randomNumber(MIN_MAIN_CLASS_SIZE, MAX_MAIN_CLASS_SIZE);

        mainClass = new Room(name, width, heigh, RoomType.MAIN_CLASS);
    }

    public void initializeWorld(RoomFactory roomFactory, RandomGenerator rnd) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream("resources/jsonFiles/worldGenerator.json")) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
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