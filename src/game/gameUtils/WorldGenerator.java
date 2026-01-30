package game.gameUtils;

import game.characters.Player;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;

public class WorldGenerator {
    private ArrayList<Room> rooms;

    public WorldGenerator() {
        this.rooms = new ArrayList<>();
    }

    public void initializeWorld(RoomFactory roomFactory, RandomGenerator rnd, Player player) {
        //TODO: Doimplementovat metodu clearWayFromTeacherToDoor();
        for (int i = 0; i < player.getRoomsLeftCount(); i++) rooms.add(roomFactory.generateRoom(rnd));
//        for (Room room : rooms) roomFactory.clearWayFromDoorToTeacher(room);
    }

    public void connectAllRooms(Room center, RoomFactory roomFactory, RandomGenerator rnd) {
        for (Room room : rooms) roomFactory.connectRooms(center, room, rnd);
    }

    public Room initializeCenterRoom(Player player, RandomGenerator rnd, RoomFactory roomFactory) {
        Room mainClass = new Room("Učebna č.1", rnd.randomNumber(3, 15), rnd.randomNumber(3,15));
        Room result = new Room("Chodba", player.getRoomsLeftCount() * 2, rnd.randomNumber(4, 6));
        roomFactory.connectRooms(result, mainClass, rnd);
        return result;
    }
}