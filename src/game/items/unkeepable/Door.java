package game.items.unkeepable;

import game.characters.Player;
import game.gameUtils.Coordinates;
import game.gameUtils.Room;
import game.items.Item;
import game.uiUtils.RandomGenerator;

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

    public Door getNextDoor() {
        return nextDoor;
    }

    public void setNextDoor(Door nextDoor) {
        this.nextDoor = nextDoor;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "next room: " + nextRoom +
                ", next door: " + nextDoor +
                ", is open: " + isOpen;
    }

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

    public void lockConnectedDoors() {
        if (nextDoor != null) {
            isOpen = false;
            nextDoor.isOpen = false;
        }
    }

    @Override
    public boolean isKeepable() {
        return false;
    }

    @Override
    public String getPathFile() {
        return "";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDEAA";
    }
}