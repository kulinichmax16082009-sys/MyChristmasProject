package game.inventories;

import game.items.Item;

import java.util.ArrayList;

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

    private boolean isFull() {
        return capacity == 0;
    }

    public boolean addItem(Item item) {
        if (!isFull()) {
            items.add(item);
            capacity--;
            return true;
        }
        return false;
    }

    public void removeItem(Item item) {
        items.remove(item);
        capacity++;
    }

    @Override
    public String toString() {
        if (items.isEmpty()) return "Váš batoh je prázdný" + "\n" + "Kapacita: " + capacity;
        return "Váš batoh: " + items + "\n" + "Kapacita: " + capacity;
    }
}
