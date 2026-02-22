package game.inventories;

import game.items.Item;

import java.util.ArrayList;

/**
 * This class represents player's inventory of items and it's own capacity
 *
 * @author Maksym Kulynych
 */
public class Inventory {
    private ArrayList<Item> items;
    private int capacity;

    public Inventory() {
        items = new ArrayList<>();
        this.capacity = 0;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * This method checks if inventory's capacity doesn't equal 0
     * @return true - capacity is 0, inventory is full. false - capacity isn't 0, inventory isn't full
     */
    private boolean isFull() {
        return capacity == 0;
    }

    /**
     * This method simply adds some item in player's inventory and decreases capacity
     * @param item item that must be put into inventory
     * @return true - item has been added successfully, false - item hasn't been added
     */
    public boolean addItem(Item item) {
        if (!isFull()) {
            items.add(item);
            capacity--;
            return true;
        }
        return false;
    }

    /**
     * This method simply removes item from inventory and increases capacity
     * @param item item that must be removed
     */
    public void removeItem(Item item) {
        items.remove(item);
        capacity++;
    }

    /**
     * This method is player's inventory of items converted into String
     * @return all player's items in bag
     */
    @Override
    public String toString() {
        if (items.isEmpty()) return "Váš batoh je prázdný" + "\n" + "Kapacita: " + capacity;
        return "Váš batoh: " + items + "\n" + "Kapacita: " + capacity;
    }
}
