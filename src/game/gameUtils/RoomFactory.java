package game.gameUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.teachers.Teacher;
import game.exceptions.BadRoomCharacteristicsFormatException;
import game.exceptions.BadRoomSizesException;
import game.items.Item;
import game.items.keepable.*;
import game.items.unkeepable.*;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * This class is used to generate rooms in the game world and to connect them together
 *
 * @author Maksym Kulynych
 */
public class RoomFactory {
    private ArrayList<Item> allPossibleItems;
    private int minRoomNumber;
    private int maxRoomNumber;
    private int minRoomSize;
    private int maxRoomSize;
    private int minSideRoomSize;
    private int maxSideRoomSize;
    private float sideRoomProbability;
    private float doorLockProbability;

    public RoomFactory() {
        allPossibleItems = new ArrayList<>();
        minRoomNumber = 2;
        maxRoomNumber = 100;
        minRoomSize = 5;
        maxRoomSize = 15;
        minSideRoomSize = 5;
        maxSideRoomSize = 7;
        sideRoomProbability = 50.0F;
        doorLockProbability = 5.0F;
    }

    /**
     * This method initializes all room factory characteristics from .json file
     */
    public void initializeRoomFactory() {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream("resources/jsonFiles/worldAndRooms/roomFactory.json")) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            throw new BadRoomCharacteristicsFormatException();
        }

        if (minRoomSize <= 0 || maxRoomSize <= 0 || maxRoomSize <= minRoomSize ||
                minSideRoomSize <= 0 || maxSideRoomSize <= 0 || maxSideRoomSize <= minSideRoomSize) {
            throw new BadRoomSizesException();
        }

        if (minRoomNumber == maxRoomNumber) maxRoomNumber++;
    }

    /**
     * This method generates new room with random name, size and items in it. It also places teacher in random coordinates and generates side room with some probability
     * @param rnd is used to generate random name, size, items and side room
     * @return generated room
     */
    public Room generateRoom(RandomGenerator rnd) {
        String name = "Učebna č." + rnd.randomNumber(minRoomNumber, maxRoomNumber);
        int width = rnd.randomNumber(minRoomSize, maxRoomSize);
        int height = rnd.randomNumber(minRoomSize, maxRoomSize);
        Room newRoom = new Room(name, width, height, RoomType.CLASSROOM);

        Coordinates teacherCoords = new Coordinates(rnd.randomNumber(0, width - 1), rnd.randomNumber(0, height - 1));
        newRoom.place(teacherCoords, Teacher.teacherFactory(rnd.randomNumber(1, 5), rnd));
        generateItems(newRoom, rnd);

        //Další teacherOffice v učebně
        if (rnd.generateProbability(sideRoomProbability)) {
            connectRooms(generateSideRoom(rnd), newRoom, rnd);
        }

        return newRoom;
    }

    /**
     * This method generates side room with random name, size and items in it. It is used to connect it with main classroom
     * @param rnd is used to generate random name, size and items
     * @return generated side room
     */
    private Room generateSideRoom(RandomGenerator rnd) {
        String name = "Kabinet č." + rnd.randomNumber(minRoomNumber, maxRoomNumber);
        int width = rnd.randomNumber(minSideRoomSize, maxSideRoomSize);
        int height = rnd.randomNumber(minSideRoomSize, maxSideRoomSize);
        Room sideRoom = new Room(name, width, height, RoomType.TEACHER_OFFICE);
        generateItems(sideRoom, rnd);

        return sideRoom;
    }

    /** This method connects two rooms together by placing doors in them. It also has some probability to lock doors
     * @param roomA first room, which will be connected
     * @param roomB second room, which will be connected
     * @param rnd is used to generate random doors and to decide if doors will be locked or not
     */
    public void connectRooms(Room roomA, Room roomB, RandomGenerator rnd) {
        Door doorA = new Door();
        Door doorB = new Door();

        doorA.setNextRoom(roomB);
        doorA.setNextDoor(doorB);
        doorB.setNextRoom(roomA);
        doorB.setNextDoor(doorA);

        if (shouldLockDoors(roomA, roomB, rnd)) doorA.setConnectedDoorsOpen(false);

        //Přidání dveří do místností
        placeDoor(roomA, doorA, rnd);
        placeDoor(roomB, doorB, rnd);
    }

    /** This method generates items in the room. It checks every cell in the room and with some probability it generates item on it.
     *  It also checks if there is reserved position on cell, where item will be generated, if there is reserved position, it will not generate item on this cell
     * @param room where items will be generated
     * @param rnd is used to generate random items and to decide if item will be generated on cell or not
     */
    public void generateItems(Room room, RandomGenerator rnd) {
        initPossibleItems();
        for (int i = 0; i < room.getHeight(); i++) {
            for (int j = 0; j < room.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(j, i);

                //Předem rezervované pozice (počáteční pozice hráče a pozice učitele)
                if (isReserved(j, i, room)) continue;

                //generace předmětů
                for (Item possibleItem : allPossibleItems) {
                    if (rnd.generateProbability(possibleItem.getSpawnChance()) && possibleItem.getMaxCount() != 0) {
                        room.place(coordinates, possibleItem.initializeItem());
                        possibleItem.subMaxCount(1);
                        break;
                    }
                }
            }
        }
    }

    /**
     * This method initializes all possible items in the game and adds them to list, which is used to generate items in rooms. It also resets max count of each item, so it can be generated again in next rooms
     */
    public void initPossibleItems() {
        allPossibleItems.clear();
        allPossibleItems.add(new MagicPear().initializeItem());
        allPossibleItems.add(new GoldenKey().initializeItem());
        allPossibleItems.add(new EnergyDrink().initializeItem());
        allPossibleItems.add(new Resistor().initializeItem());
        allPossibleItems.add(new Chair().initializeItem());
        allPossibleItems.add(new Wall().initializeItem());
        allPossibleItems.add(new Desk().initializeItem());
    }

    /** This method clears way from hall door to teacher in the room.
     * It finds hall door and teacher in the room and then it removes all objects between them, so player can move from hall door to teacher without any obstacles
     * @param room where way will be cleared
     */
    public void clearWayFromHallDoorToTeacher(Room room) {
        Door hallDoor = findHallDoor(room);
        Teacher teacher = findTeacher(room);

        if (teacher == null || hallDoor == null) return;

        clearPathBetween(hallDoor.getCoordinates(), teacher.getCoordinates(), room);
    }

    /** This method finds door, which leads to hall, in the room
     * @param room where hall door will be found
     * @return hall door, if there is such door in the room, null if there is no such door in the room
     */
    private Door findHallDoor(Room room) {
        for (GameObject obj : room.getGameObjects().values()) {
            if (obj instanceof Door door && door.getNextRoom().getRoomType().equals(RoomType.HALL)) {
                return door;
            }
        }
        return null;
    }

    /** This method finds teacher in the room
     * @param room where teacher will be found
     * @return teacher, if there is such teacher in the room, null if there is no such teacher in the room
     */
    private Teacher findTeacher(Room room) {
        for (GameObject obj : room.getGameObjects().values()) {
            if (obj instanceof Teacher teacher) {
                return teacher;
            }
        }
        return null;
    }

    /** This method removes all objects between two coordinates in the room. It is used to clear way from hall door to teacher in the room
     * It removes all objects in straight line between two coordinates and also removes object in corner, if there is one
     * @param start coordinates of first point (hall door)
     * @param end coordinates of second point (teacher)
     * @param room where objects will be removed
     */
    private void clearPathBetween(Coordinates start, Coordinates end, Room room) {
        int diffX = end.getX() - start.getX();
        int diffY = end.getY() - start.getY();

        for (int i = 1; i < Math.abs(diffX); i++) {
            int x = start.getX() + Integer.signum(diffX) * i;
            Coordinates pos = new Coordinates(x, start.getY());
            room.getGameObjects().remove(pos);
        }

        Coordinates corner = new Coordinates(end.getX(), start.getY());
        if (!corner.equals(start) && !corner.equals(end)) {
            room.getGameObjects().remove(corner);
        }

        for (int i = 1; i < Math.abs(diffY); i++) {
            int y = start.getY() + Integer.signum(diffY) * i;
            Coordinates pos = new Coordinates(end.getX(), y);
            room.getGameObjects().remove(pos);
        }
    }

    /** This method checks if there is reserved position on cell, where item will be generated. It reserves starting position of player, position of teacher and center of room, so there will be more space around teacher for player to move
     * @param x x coordinate of cell, where item will be generated
     * @param y y coordinate of cell, where item will be generated
     * @param room where item will be generated
     * @return true - there is reserved position on cell, false - there is no reserved position on cell
     */
    private boolean isReserved(int x, int y, Room room) {
        boolean isStart = (x == 0 && y == 0);
        boolean isCenter = (x == room.getWidth() / 2 && y == room.getHeight() / 2);
        boolean isOppositeCorner = (x == room.getWidth() - 1 && y == room.getHeight() - 1);

        return isCenter || isStart || isOppositeCorner;
    }

    /** This method removes all items around door in the room
     * It checks all 8 directions around door and removes all items in these directions, so there will be more space around door for player to move
     * @param room where items will be removed
     * @param door around which items will be removed
     */
    private void clearItemsAroundDoor(Room room, Door door) {
        for (int i = 0; i < 8; i++) {
            door.removeObjectNearByType(Item.class, true, room);
            door.removeObjectNearByType(Item.class, false, room);
        }
    }

    /** This method checks if doors between two rooms should be locked. Doors will be locked if one of rooms is main classroom or with some probability, so there will be more variety in the game
     * @param roomA first room, which will be connected
     * @param roomB second room, which will be connected
     * @param rnd is used to decide if doors will be locked or not
     * @return true - doors should be locked, false - doors should not be locked
     */
    private boolean shouldLockDoors(Room roomA, Room roomB, RandomGenerator rnd) {
        return roomA.getRoomType().equals(RoomType.MAIN_CLASS) ||
                roomB.getRoomType().equals(RoomType.MAIN_CLASS) ||
                rnd.generateProbability(doorLockProbability);
    }

    /** This method places door in the room, if there is another door near it,
     * it will move door to another random free coordinates until there is no other door near.
     * It also clears items around door after placing
     * @param room where door will be placed
     * @param door which will be placed
     * @param rnd is used to find random free coordinates for door
     */
    private void placeDoor(Room room, Door door, RandomGenerator rnd) {
        room.place(room.findRandomFreeCoordinates(rnd), door);
        while (door.isObjectNearByType(Door.class, false, room)) {
            room.move(room.findRandomFreeCoordinates(rnd), door);
        }
        clearItemsAroundDoor(room, door);
    }

    public ArrayList<Item> getAllPossibleItems() {
        return allPossibleItems;
    }

    public int getMinRoomNumber() {
        return minRoomNumber;
    }

    public int getMaxRoomNumber() {
        return maxRoomNumber;
    }

    public int getMinRoomSize() {
        return minRoomSize;
    }

    public int getMaxRoomSize() {
        return maxRoomSize;
    }

    public int getMinSideRoomSize() {
        return minSideRoomSize;
    }

    public int getMaxSideRoomSize() {
        return maxSideRoomSize;
    }

    public float getSideRoomProbability() {
        return sideRoomProbability;
    }

    public float getDoorLockProbability() {
        return doorLockProbability;
    }
}