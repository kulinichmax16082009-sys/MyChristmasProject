package game.characters;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.exceptions.BadIntelligenceException;
import game.exceptions.BadInventoryCapacityException;
import game.exceptions.BadPlayerCharacteristicsFormatException;
import game.exceptions.BadRequiredOnesAmountException;
import game.gameUtils.*;
import game.inventories.*;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * This class represents player in game
 *
 * @author Maksym Kulynych
 */
public class Player extends Character {
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
        this.currentRoom = null;
        this.tasks = new ArrayList<>();
        this.marks = new Marks();
        this.inventory = new Inventory();
        this.isTalking = false;
        this.oneStepDistance = 1;
        this.maxTasksCount = 1;
        this.requiredOnesAmount = 8;
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

    /**
     * This method generates random amount of tasks that must be added to player's tasks in range of 1 to maxTasksCount
     * @param rnd is used to generate random number
     * @return tasks amount
     */
    public int getRandomTasksCount(RandomGenerator rnd) {
        return rnd.randomNumber(1, maxTasksCount);
    }

    /**
     * This method simply adds task to player's tasks inventory
     * @param task task that must be added
     */
    public void addTask(Task task) {
        if (tasks.size() <= maxTasksCount) tasks.add(task);
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDDCD";
    }

    /**
     * This method initializes all player's characteristics from .json file
     */
    public void initializePlayer() {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream("resources/jsonFiles/player.json")) {
            mapper.readerForUpdating(this).readValue(input);
        } catch (Exception e) {
            throw new BadPlayerCharacteristicsFormatException();
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
    }

    /**
     * This method checks if player has any amount of intelligence left
     * @return true - player has no intelligence, false - player has any intelligence
     */
    public boolean hasNoIntelligence() {
        return getIntelligence() <= 0;
    }
}