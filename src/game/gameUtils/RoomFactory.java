package game.gameUtils;

import game.characters.teachers.Teacher;
import game.items.Item;
import game.items.keepable.*;
import game.items.unkeepable.*;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;

public class RoomFactory {
    ArrayList<Item> allPossibleItems;

    public RoomFactory() {
        allPossibleItems = new ArrayList<>();
        initializeAllPossibleItems();
    }

    public Room generateRoom(RandomGenerator rnd) {
        Room room = new Room("Učebna č." + rnd.randomNumber(2, 100), rnd.randomNumber(2, 15), rnd.randomNumber(2, 15));
        room.place(new Coordinates(rnd.randomNumber(0, room.getWidth() - 1), rnd.randomNumber(0, room.getHeight() - 1)), Teacher.teacherFactory(rnd.randomNumber(1,5)));
        generateItems(room, rnd);

        //Další kabinet v učebně
        if (rnd.generateProbability(50)) {
            Room sideRoom = new Room("Kabinet č." + rnd.randomNumber(1, 100), rnd.randomNumber(2, 6), rnd.randomNumber(2, 6));
            generateItems(sideRoom, rnd);
            connectRooms(room, sideRoom, rnd);
        }
        return room;
    }

    public void connectRooms(Room roomA, Room roomB, RandomGenerator rnd) {
        Door doorA = new Door();
        Door doorB = new Door();

        doorA.setNextRoom(roomB);
        doorA.setNextDoor(doorB);
        doorB.setNextRoom(roomA);
        doorB.setNextDoor(doorA);

        if (roomA.getName().equals("Učebna č.1") || roomB.getName().equals("Učebna č.1")) lockConnectedDoors(doorA, doorB);
        if (rnd.generateProbability(5)) lockConnectedDoors(doorA, doorB);

        //Přidání dveří do místností
        placeDoor(roomA, doorA, rnd);
        placeDoor(roomB, doorB, rnd);

        //Odstranění překážek kolem dveři
        clearItemsAroundDoor(roomA, doorA);
        clearItemsAroundDoor(roomB, doorB);
    }

    public void generateItems(Room room, RandomGenerator rnd) {
        for (int i = 0; i < room.getHeight(); i++) {
            for (int j = 0; j < room.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(j, i);

                //Předem rezervované pozice (počáteční pozice hráče a pozice učitele)
                if (isReserved(j, i, room)) continue;

                //generace předmětů
                for (Item possibleItem : allPossibleItems) {
                    Item item = possibleItem.initializeItem();
                    if (rnd.generateProbability(possibleItem.getSpawnChance()) && possibleItem.getMaxCount() != 0) {
                        room.place(coordinates, item);
                        possibleItem.subMaxCount(1);
                    }
                }
            }
        }
    }

    public void initializeAllPossibleItems() {
        allPossibleItems.add(new MagicPear().initializeItem());
        allPossibleItems.add(new GoldenKey().initializeItem());
        allPossibleItems.add(new EnergyDrink().initializeItem());
        allPossibleItems.add(new EnergyDrink().initializeItem());
        allPossibleItems.add(new Resistor().initializeItem());
        allPossibleItems.add(new Chair().initializeItem());
        allPossibleItems.add(new Wall().initializeItem());
        allPossibleItems.add(new Desk().initializeItem());
    }

    public void clearWayFromDoorToTeacher(Room room) {
        Teacher teacher = null;
        Door door = null;

        for (GameObject gameObject : room.getGameObjects().values()) {
            if (gameObject instanceof Door) door = (Door) gameObject;
            if (gameObject instanceof Teacher) teacher = (Teacher) gameObject;
        }

        if (teacher == null || door == null) return;

        Coordinates doorCoordinates = door.getCoordinates();

        //Vypočet rozdílu souřadnic
        int coordinatesDiffX = teacher.getCoordinates().getX() - door.getCoordinates().getX();
        int coordinatesDiffY = teacher.getCoordinates().getY() - door.getCoordinates().getY();

        //X souřadnice
        for (int i = 1; i < Math.abs(coordinatesDiffX); i++) {
            room.getGameObjects().remove(new Coordinates(doorCoordinates.getX() + Integer.signum(coordinatesDiffX) * i, doorCoordinates.getY()));
        }

        //Y souřadnice
        for (int i = 0; i < Math.abs(coordinatesDiffY); i++) {
            room.getGameObjects().remove(new Coordinates(doorCoordinates.getX() + coordinatesDiffX, doorCoordinates.getY() + Integer.signum(coordinatesDiffY) * i));
        }
    }

    public boolean isReserved(int x, int y, Room room) {
        return x == 0 && y == 0 || x == room.getWidth() - 1 && y == room.getHeight() - 1;
    }

    public void clearItemsAroundDoor(Room room, Door door) {
        for (int i = 0; i < 8; i++) {
            Item deletedKeepable = door.getAnyItemNear(true, room);
            Item deletedUnkeepable = door.getAnyItemNear(false, room);
            if (deletedUnkeepable != null) room.getGameObjects().remove(deletedUnkeepable.getCoordinates());
            if (deletedKeepable != null) room.getGameObjects().remove(deletedKeepable.getCoordinates());
        }
    }

    public void lockConnectedDoors(Door doorA, Door doorB) {
        if (doorA.getNextDoor() == doorB && doorB.getNextDoor() == doorA) {
            doorA.setOpen(false);
            doorB.setOpen(false);
        }
    }

    private void placeDoor(Room room, Door door, RandomGenerator rnd) {
        room.place(randomFree(room, rnd), door);
        while (door.isAnyDoorNear(room)) {
            room.move(randomFree(room, rnd), door);
        }
    }

    private Coordinates randomFree(Room room, RandomGenerator rnd) {
        return room.findFreeCoordinates().get(rnd.randomNumber(0, room.findFreeCoordinates().size() - 1));
    }
}