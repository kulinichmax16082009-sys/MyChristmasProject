package game.gameUtils;

import game.characters.Player;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;

public class WorldGenerator {
    private Room hall;
    private ArrayList<Room> rooms;

    public WorldGenerator() {
        this.rooms = new ArrayList<>();
    }

    public void initializeWorld(RoomFactory roomFactory, RandomGenerator rnd, Player player) {
        for (int i = 0; i < player.getRoomsLeftCount(); i++) rooms.add(roomFactory.generateRoom(rnd));
    }

    public void connectAllRooms(RoomFactory roomFactory, RandomGenerator rnd) {
        for (Room room : rooms) roomFactory.connectRooms(hall, room, rnd);
        for (Room room : rooms) roomFactory.clearWayFromHallDoorToTeacher(room, hall);
    }

    public void initializeHall(Player player, RandomGenerator rnd, RoomFactory roomFactory) {
        Room mainClass = new Room("Učebna č.1", rnd.randomNumber(3, 15), rnd.randomNumber(3,15));
        hall = new Room("Chodba", player.getRoomsLeftCount() * 2, rnd.randomNumber(4, 6));
        roomFactory.connectRooms(hall, mainClass, rnd);
    }

    public Room getHall() {
        return hall;
    }

    public void setHall(Room hall) {
        this.hall = hall;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}