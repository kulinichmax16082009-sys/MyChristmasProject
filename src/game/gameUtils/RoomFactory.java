package game.gameUtils;

import game.characters.teachers.Teacher;
import game.items.Item;
import game.items.keepable.*;
import game.items.unkeepable.*;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;

public class RoomFactory {
    private ArrayList<Item> allPossibleItems;
    private static final int MIN_ROOM_NUMBER = 2;
    private static final int MAX_ROOM_NUMBER = 100;
    private static final int MIN_ROOM_SIZE = 3;
    private static final int MAX_ROOM_SIZE = 15;
    private static final int MIN_SIDE_ROOM_SIZE = 3;
    private static final int MAX_SIDE_ROOM_SIZE = 6;
    private static final float SIDE_ROOM_PROBABILITY = 50.0F;
    private static final float DOOR_LOCK_PROBABILITY = 5.0F;

    public RoomFactory() {
        allPossibleItems = new ArrayList<>();
    }

    public Room generateRoom(RandomGenerator rnd) {
        String name = "Učebna č." + rnd.randomNumber(MIN_ROOM_NUMBER, MAX_ROOM_NUMBER);
        int width = rnd.randomNumber(MIN_ROOM_SIZE, MAX_ROOM_SIZE);
        int height = rnd.randomNumber(MIN_ROOM_SIZE, MAX_ROOM_SIZE);
        Room newRoom = new Room(name, width, height);

        Coordinates teacherCoords = new Coordinates(rnd.randomNumber(0, width - 1), rnd.randomNumber(0, height - 1));
        newRoom.place(teacherCoords, Teacher.teacherFactory(rnd.randomNumber(1, 5)));
        generateItems(newRoom, rnd);

        //Další kabinet v učebně
        if (rnd.generateProbability(SIDE_ROOM_PROBABILITY)) {
            connectRooms(generateSideRoom(rnd), newRoom, rnd);
        }

        return newRoom;
    }

    private Room generateSideRoom(RandomGenerator rnd) {
        String name = "Kabinet č." + rnd.randomNumber(MIN_ROOM_NUMBER, MAX_ROOM_NUMBER);
        int width = rnd.randomNumber(MIN_SIDE_ROOM_SIZE, MAX_SIDE_ROOM_SIZE);
        int height = rnd.randomNumber(MIN_SIDE_ROOM_SIZE, MAX_SIDE_ROOM_SIZE);
        Room sideRoom = new Room(name, width, height);
        generateItems(sideRoom, rnd);

        return sideRoom;
    }

    public void connectRooms(Room roomA, Room roomB, RandomGenerator rnd) {
        Door doorA = new Door();
        Door doorB = new Door();

        doorA.setNextRoom(roomB);
        doorA.setNextDoor(doorB);
        doorB.setNextRoom(roomA);
        doorB.setNextDoor(doorA);

        if (shouldLockDoors(roomA, roomB, rnd)) doorA.lockConnectedDoors();

        //Přidání dveří do místností
        placeDoor(roomA, doorA, rnd);
        placeDoor(roomB, doorB, rnd);
    }

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

    public void clearWayFromHallDoorToTeacher(Room room, Room hall) {
        Door hallDoor = findHallDoor(room, hall);
        Teacher teacher = findTeacher(room);

        if (teacher == null || hallDoor == null) return;

        clearPathBetween(hallDoor.getCoordinates(), teacher.getCoordinates(), room);
    }

    private Door findHallDoor(Room room, Room hall) {
        for (GameObject obj : room.getGameObjects().values()) {
            if (obj instanceof Door door && door.getNextRoom() == hall) {
                return door;
            }
        }
        return null;
    }

    private Teacher findTeacher(Room room) {
        for (GameObject obj : room.getGameObjects().values()) {
            if (obj instanceof Teacher teacher) {
                return teacher;
            }
        }
        return null;
    }

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

    private boolean isReserved(int x, int y, Room room) {
        boolean isStart = (x == 0 && y == 0);
        boolean isCenter = (x == room.getWidth() / 2 && y == room.getHeight() / 2);
        boolean isOppositeCorner = (x == room.getWidth() - 1 && y == room.getHeight() - 1);

        return isCenter || isStart || isOppositeCorner;
    }

    private void clearItemsAroundDoor(Room room, Door door) {
        for (int i = 0; i < 8; i++) {
            door.removeObjectNearByType(Item.class, true, room);
            door.removeObjectNearByType(Item.class, false, room);
        }
    }

    private boolean shouldLockDoors(Room roomA, Room roomB, RandomGenerator rnd) {
        return roomA.getName().equals("Učebna č.1") ||
                roomB.getName().equals("Učebna č.1") ||
                rnd.generateProbability(DOOR_LOCK_PROBABILITY);
    }

    private void placeDoor(Room room, Door door, RandomGenerator rnd) {
        room.place(room.findRandomFreeCoordinates(rnd), door);
        while (door.isObjectNearByType(Door.class, false, room)) {
            room.move(room.findRandomFreeCoordinates(rnd), door);
        }
        clearItemsAroundDoor(room, door);
    }
}