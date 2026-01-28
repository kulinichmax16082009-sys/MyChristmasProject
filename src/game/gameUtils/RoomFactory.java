package game.gameUtils;

import game.characters.teachers.Teacher;
import game.items.Item;
import game.items.keepable.EnergyDrink;
import game.items.keepable.GoldenKey;
import game.items.keepable.MagicPear;
import game.items.keepable.Resistor;
import game.items.unkeepable.Chair;
import game.items.unkeepable.Desk;
import game.items.unkeepable.Door;
import game.items.unkeepable.Wall;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;

public class RoomFactory {
    ArrayList<Item> allPossibleItems;

    public RoomFactory() {
        allPossibleItems = new ArrayList<>();
    }

    //TODO: Učit jména učeben z externího souboru (nebo generovat podle nějakého vzoru)
    public Room generateRoom(RandomGenerator rnd) {
        Room room = new Room("Učebna č." + rnd.randomNumber(1, 100), rnd.randomNumber(2, 15), rnd.randomNumber(2, 15));
        room.place(new Coordinates(rnd.randomNumber(0, room.getWidth() - 1), rnd.randomNumber(0, room.getHeight() - 1)), Teacher.teacherFactory(rnd.randomNumber(1,5)));
        generateItems(room, rnd);
        return room;
    }

    //TODO: Ošetřít podmínku při které není možné dveře umístit
    public void connectRooms(Room roomA, Room roomB, RandomGenerator rnd) {
        Door doorA = new Door();
        Door doorB = new Door();

        doorA.setNextRoom(roomB);
        doorA.setNextDoor(doorB);
        doorB.setNextRoom(roomA);
        doorB.setNextDoor(doorA);

        boolean isPlaced = false;
        while (!isPlaced) isPlaced = roomA.place(new Coordinates(rnd.randomNumber(0, roomA.getWidth() - 1), rnd.randomNumber(0, roomA.getHeight() - 1)), doorA);
        isPlaced = false;
        while (!isPlaced) isPlaced = roomB.place(new Coordinates(rnd.randomNumber(0, roomB.getWidth() - 1), rnd.randomNumber(0, roomB.getHeight() - 1)), doorB);

        for (int i = 0; i < 7; i++) {
            if (!doorA.isAnyObjectNearByType(Teacher.class, false)) {
                roomA.getGameObjects().remove(doorA.getNearObjectByType(GameObject.class, false).getCoordinates());
            }
        }

        for (int i = 0; i < 7; i++) {
            if (!doorB.isAnyObjectNearByType(Teacher.class, false)) {
                roomB.getGameObjects().remove(doorB.getNearObjectByType(GameObject.class, false).getCoordinates());
            }
        }
    }

    public void generateItems(Room room, RandomGenerator rnd) {
        initializeAllPossibleItems();
        for (int i = 0; i < room.getHeight(); i++) {
            for (int j = 0; j < room.getWidth(); j++) {
                for (Item possibleItem : allPossibleItems) {
                    if (rnd.generateProbability(possibleItem.getSpawnChance()) && !possibleItem.isAnyObjectNearByType(GameObject.class, false)) {
                        room.place(new Coordinates(j, i), possibleItem);
                    }
                }
            }
        }
    }

    public void initializeAllPossibleItems() {
        allPossibleItems.add(new Chair().initializeItem());
        allPossibleItems.add(new Wall().initializeItem());
        allPossibleItems.add(new Desk().initializeItem());
        allPossibleItems.add(new Resistor().initializeItem());
        allPossibleItems.add(new EnergyDrink().initializeItem());
        allPossibleItems.add(new GoldenKey().initializeItem());
        allPossibleItems.add(new MagicPear().initializeItem());
    }

    public void clearWayFromDoorToTeacher(Room room) {
        int coordinatesDiffX;
        int coordinatesDiffY;

        Teacher teacher = null;
        Door door = null;

        for (GameObject gameObject : room.getGameObjects().values()) {
            if (gameObject instanceof Door) door = (Door) gameObject;
            if (gameObject instanceof Teacher) teacher = (Teacher) gameObject;
        }

        if (teacher == null || door == null) return;

        Coordinates doorCoordinates = door.getCoordinates();

        coordinatesDiffX = teacher.getCoordinates().getX() - door.getCoordinates().getX();
        coordinatesDiffY = teacher.getCoordinates().getY() - door.getCoordinates().getY();

        //X axis
        for (int i = 1; i < Math.abs(coordinatesDiffX); i++) {
            room.getGameObjects().remove(new Coordinates(doorCoordinates.getX() + Integer.signum(coordinatesDiffX) * i, doorCoordinates.getY()));
        }

        //Y axis
        for (int i = 0; i < Math.abs(coordinatesDiffY); i++) {
            room.getGameObjects().remove(new Coordinates(doorCoordinates.getX() + coordinatesDiffX, doorCoordinates.getY() + Integer.signum(coordinatesDiffY) * i));
        }
    }
}