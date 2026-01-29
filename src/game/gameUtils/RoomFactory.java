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
        //TODO: Ošetřit aby dveře bylo možné umístit

        Door doorA = new Door();
        Door doorB = new Door();

        doorA.setNextRoom(roomB);
        doorA.setNextDoor(doorB);
        doorB.setNextRoom(roomA);
        doorB.setNextDoor(doorA);

        //Přidání dveří do místností
        boolean isPlaced = false;

        while (!isPlaced) {
            isPlaced = roomA.place(new Coordinates(rnd.randomNumber(0, roomA.getWidth() - 1), rnd.randomNumber(0, roomA.getHeight() - 1)), doorA);
        }
        isPlaced = false;
        while (!isPlaced) {
            isPlaced = roomB.place(new Coordinates(rnd.randomNumber(0, roomB.getWidth() - 1), rnd.randomNumber(0, roomB.getHeight() - 1)), doorB);
        }

        //Odstranění překážek kolem dveři
        for (int i = 0; i < 8; i++) {
            Item deletedA = doorA.getAnyItemNear(false, roomA);
            if (deletedA != null) roomA.getGameObjects().remove(deletedA.getCoordinates());
        }

        for (int j = 0; j < 8; j++) {
            Item deletedB = doorB.getAnyItemNear(false, roomB);
            if (deletedB != null) roomB.getGameObjects().remove(deletedB.getCoordinates());
        }
    }

    public void generateItems(Room room, RandomGenerator rnd) {
        //TODO: generovat šanci pouze jednou
        initializeAllPossibleItems();
        for (int i = 0; i < room.getHeight(); i++) {
            for (int j = 0; j < room.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(j, i);
                //Předem rezervované pozice (počáteční pozice hráče a pozice učitele)
                if (coordinates.equals(new Coordinates(0,0))) continue;
                if (coordinates.equals(new Coordinates(room.getWidth() - 1, room.getHeight() -1))) continue;
                for (Item possibleItem : allPossibleItems) {
                    Item item = possibleItem.initializeItem();
                    if (rnd.generateProbability(possibleItem.getSpawnChance())) {
                        room.place(coordinates, item);
                    }
                }
            }
        }
    }

    //TODO: Opravit aby šance byla menší
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

        //Vypočet rozdílu souřadnic
        coordinatesDiffX = teacher.getCoordinates().getX() - door.getCoordinates().getX();
        coordinatesDiffY = teacher.getCoordinates().getY() - door.getCoordinates().getY();

        //X souřadnice
        for (int i = 1; i < Math.abs(coordinatesDiffX); i++) {
            room.getGameObjects().remove(new Coordinates(doorCoordinates.getX() + Integer.signum(coordinatesDiffX) * i, doorCoordinates.getY()));
        }

        //Y souřadnice
        for (int i = 0; i < Math.abs(coordinatesDiffY); i++) {
            room.getGameObjects().remove(new Coordinates(doorCoordinates.getX() + coordinatesDiffX, doorCoordinates.getY() + Integer.signum(coordinatesDiffY) * i));
        }
    }
}