package game.items.unkeepable;

import game.characters.Player;
import game.gameUtils.Room;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class Door extends Item {
    private Room nextRoom;
    private Door nextDoor;
    private boolean isOpen;

    public Door(String name, Room nextRoom, Door nextDoor, boolean isOpen) {
        super(name);
        this.nextRoom = nextRoom;
        this.nextDoor = nextDoor;
        this.isOpen = isOpen;
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "next room: " + nextRoom +
                ", next door: " + nextDoor +
                ", is open: " + isOpen;
    }

    //TODO: Implement useAbility method
    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
    }

    @Override
    public boolean isKeepable() {
        return false;
    }

    @Override
    public String getSprite() {
        return "";
    }
}
