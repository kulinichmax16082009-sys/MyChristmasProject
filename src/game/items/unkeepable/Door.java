package game.items.unkeepable;

import game.characters.Player;
import game.gameUtils.Coordinates;
import game.gameUtils.Room;
import game.items.Item;
import game.uiUtils.RandomGenerator;

/**
 * This class represents item 'door' in game, which is used to transport between 2 places
 *
 * @author Maksym Kulynych
 */
public class Door extends Item {
    private Room nextRoom;
    private Door nextDoor;
    private boolean isOpen;

    public Door() {
        super("dveře");
        this.nextRoom = null;
        this.nextDoor = null;
        this.isOpen = true;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
    }

    public void setNextDoor(Door nextDoor) {
        this.nextDoor = nextDoor;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    @Override
    public String toString() {
        return "next room: " + nextRoom +
                ", next door: " + nextDoor +
                ", is open: " + isOpen;
    }

    /**
     * This method represents entering the door by deleting player from his current room
     * and placing near the door in next room
     * @param player is used to change some characteristics while using item
     * @param rnd is used for random effect from item ability
     */
    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
        int x = nextDoor.getCoordinates().getX();
        int y = nextDoor.getCoordinates().getY();

        int[][] directions = initDirections();

        for (int[] d : directions) {
            Coordinates coordinates = new Coordinates(x + d[0], y + d[1]);

            if (nextRoom.canBePlaced(coordinates)) {
                player.getCurrentRoom().getGameObjects().remove(player.getCoordinates());
                player.setCurrentRoom(nextRoom);
                nextRoom.place(coordinates, player);
                break;
            }
        }
    }

    /**
     * This method simply locks/unlocks to connected doors
     * @param open true - should unlock doors, false - should lock doors
     */
    public void setConnectedDoorsOpen(boolean open) {
        if (nextDoor != null) {
            this.isOpen = open;
            nextDoor.isOpen = open;
        }
    }

    @Override
    public boolean isKeepable() {
        return false;
    }

    @Override
    public String getJsonPathFile() {
        return "";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDEAA";
    }
}