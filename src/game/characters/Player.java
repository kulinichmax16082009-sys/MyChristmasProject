package game.characters;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.gameUtils.Room;
import game.inventories.Inventory;
import game.inventories.Marks;
import game.inventories.Task;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Player extends Character {
    private int roomsLeftCount;
    private int oneStepDistance;
    private Inventory inventory;
    private Marks marks;
    private Room currentRoom;
    private ArrayList<Task> tasks;
    private boolean isTalking;

    public Player() {
        super(null);
    }

    public int getRoomsLeftCount() {
        return roomsLeftCount;
    }

    public void setRoomsLeftCount(int roomsLeftCount) {
        this.roomsLeftCount = roomsLeftCount;
    }

    public int getOneStepDistance() {
        return oneStepDistance;
    }

    public void setOneStepDistance(int oneStepDistance) {
        this.oneStepDistance = oneStepDistance;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isTalking() {
        return isTalking;
    }

    public void setTalking(boolean talking) {
        isTalking = talking;
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDDCD";
    }

    //TODO: handle exceptions properly
    public void initializePlayer() {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream("resources/player.json")) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void subRoomsLeftCount(int amount) {
        if (roomsLeftCount - amount >= 0) roomsLeftCount -= amount;
        else roomsLeftCount = 0;
    }

    @Override
    public String toString() {
        return "roomsLeftCount" + roomsLeftCount +
                ", oneStepDistance=" + oneStepDistance +
                ", inventory=" + inventory +
                ", marks=" + marks +
                ", currentRoom=" + currentRoom +
                ", tasks=" + tasks +
                ", isTalking=" + isTalking;
    }
}
