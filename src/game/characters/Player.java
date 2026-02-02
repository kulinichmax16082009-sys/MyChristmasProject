package game.characters;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.gameUtils.Room;
import game.inventories.Inventory;
import game.inventories.Marks;
import game.inventories.Task;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Character {
    private int roomsLeftCount;
    private int maxTasksCount;
    private int oneStepDistance;
    private Inventory inventory;
    private Marks marks;
    private ArrayList<Task> tasks;
    private Room currentRoom;
    private boolean isTalking;

    public Player() {
        super(null);
        this.roomsLeftCount = 1;
        this.currentRoom = null;
        this.tasks = new ArrayList<>();
        this.marks = new Marks();
        this.inventory = new Inventory();
        this.isTalking = false;
        this.oneStepDistance = 1;
        this.maxTasksCount = 1;
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

    public boolean getIsTalking() {
        return isTalking;
    }

    public void setIsTalking(boolean talking) {
        isTalking = talking;
    }

    public int getMaxTasksCount() {
        return maxTasksCount;
    }

    public void setMaxTasksCount(int maxTasksCount) {
        this.maxTasksCount = maxTasksCount;
    }

    public int getRandomTasksCount(RandomGenerator rnd) {
        return rnd.randomNumber(1, maxTasksCount);
    }

    public void addTask(Task task) {
        if (tasks.size() <= maxTasksCount) tasks.add(task);
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDDCD";
    }

    public void initializePlayer() {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream("resources/player.json")) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void subRoomsLeftCount(int amount) {
        if (roomsLeftCount - amount < 0) roomsLeftCount = 0;
        else roomsLeftCount -= amount;
    }

//    @Override
//    public String toString() {
//        return "roomsLeftCount" + roomsLeftCount +
//                ", oneStepDistance=" + oneStepDistance +
//                ", inventory=" + inventory +
//                ", marks=" + marks +
//                ", currentRoom=" + currentRoom +
//                ", tasks=" + tasks +
//                ", isTalking=" + isTalking + "int" + getIntelligence();
//    }
}