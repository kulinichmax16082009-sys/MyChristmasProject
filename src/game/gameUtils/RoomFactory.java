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

    //TODO: Učit jména učeben z externího souboru + přidat ošetření cesty od učitele k dveřim
    public Room generateRoom(RandomGenerator rnd) {
        Room room = new Room("Učebna č." + rnd.randomNumber(1, 100), rnd.randomNumber(2, 15), rnd.randomNumber(2, 15));
        room.place(new Coordinates(rnd.randomNumber(0, room.getWidth() - 1), rnd.randomNumber(0, room.getHeight() - 1)), Teacher.teacherFactory(rnd.randomNumber(1,5)));
        generateItems(room, rnd);
        return room;
    }

    //TODO: Ošetřít podmínku při které není možné dveře umístit
    public void connectRooms(Room roomA, Room roomB) {
        Door doorA = new Door();
        Door doorB = new Door();

        doorA.setNextRoom(roomB);
        doorA.setNextDoor(doorB);
        doorB.setNextRoom(roomA);
        doorB.setNextDoor(doorA);

        for (int i = 0; i < roomA.findFreeCoordinates().size(); i++) {
            boolean isPlaced = roomA.place(roomA.findFreeCoordinates().get(i), doorA);
            if (isPlaced) break;
        }
        for (int i = 0; i < roomB.findFreeCoordinates().size(); i++) {
            boolean isPlaced = roomB.place(roomB.findFreeCoordinates().get(i), doorB);
            if (isPlaced) break;
        }
    }

    public void generateItems(Room room, RandomGenerator rnd) {
        initializeAllPossibleItems();
        for (int i = 0; i < room.getHeight(); i++) {
            for (int j = 0; j < room.getWidth(); j++) {
                for (Item possibleItem : allPossibleItems) {
                    if (rnd.generateProbability(possibleItem.getSpawnChance()) && !possibleItem.isAnyObjectNearByType(Item.class, true)) {
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
        allPossibleItems.add(new MagicPear().initializeItem());
        allPossibleItems.add(new GoldenKey().initializeItem());
    }
}