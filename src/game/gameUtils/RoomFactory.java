package game.gameUtils;

import game.uiUtils.RandomGenerator;

public class RoomFactory {
    public Room generateRoom(RandomGenerator rnd) {
        return new Room();
    }
    public void connectRooms(Room roomA, Room roomB) {}
    public void generateItems(Room room, RandomGenerator rnd) {}
}
