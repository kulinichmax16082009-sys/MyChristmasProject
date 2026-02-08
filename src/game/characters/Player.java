package game.characters;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.exceptions.BadIntelligenceException;
import game.exceptions.BadInventoryCapacityException;
import game.exceptions.BadRequiredOnesAmountException;
import game.exceptions.BadRoomsLeftCountException;
import game.gameUtils.*;
import game.inventories.*;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Player extends Character {
    private int minRoomsLeftCount;
    private int maxRoomsLeftCount;
    private int roomsLeftCount;
    private int maxTasksCount;
    private int oneStepDistance;
    private Inventory inventory;
    private Marks marks;
    private ArrayList<Task> tasks;
    private Room currentRoom;
    private boolean isTalking;
    private int requiredOnesAmount;

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
        this.requiredOnesAmount = 8;
        this.minRoomsLeftCount = 8;
        this.maxRoomsLeftCount = 16;
    }

    public int getRoomsLeftCount() {
        return roomsLeftCount;
    }

    public int getOneStepDistance() {
        return oneStepDistance;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Marks getMarks() {
        return marks;
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

    public boolean getIsTalking() {
        return isTalking;
    }

    public int getMaxTasksCount() {
        return maxTasksCount;
    }

    public void setIsTalking(boolean talking) {
        isTalking = talking;
    }

    public int getRequiredOnesAmount() {
        return requiredOnesAmount;
    }

    public int getMinRoomsLeftCount() {
        return minRoomsLeftCount;
    }

    public int getMaxRoomsLeftCount() {
        return maxRoomsLeftCount;
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

    public void initializePlayer(RandomGenerator rnd) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream("resources/jsonFiles/player.json")) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (minRoomsLeftCount <= 0 || maxRoomsLeftCount <= 0 || maxRoomsLeftCount <= minRoomsLeftCount) {
            throw new BadRoomsLeftCountException();
        }

        if (requiredOnesAmount <= 0) {
            throw new BadRequiredOnesAmountException();
        }

        if (getIntelligence() <= 0) {
            throw new BadIntelligenceException();
        }

        if (inventory.getCapacity() < 0) {
            throw new BadInventoryCapacityException();
        }

        roomsLeftCount = rnd.randomNumber(minRoomsLeftCount, maxRoomsLeftCount);
    }

    public void subRoomsLeftCount(int amount) {
        if (roomsLeftCount - amount < 0) roomsLeftCount = 0;
        else roomsLeftCount -= amount;
    }

    public boolean hasNoIntelligence() {
        return getIntelligence() <= 0;
    }

    public boolean hasNoRoomsLeft() {
        return roomsLeftCount == 0;
    }

    public void visitRoom() {
        if (!currentRoom.getIsVisited() && currentRoom.getRoomType().equals(RoomType.CLASSROOM)) {
            subRoomsLeftCount(1);
            currentRoom.setIsVisited(true);
        }
    }
}