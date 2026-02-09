package game.inventories;

import game.items.Item;
import game.uiUtils.Colors;

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
        if (items.isEmpty()) {
            return Colors.BRIGHT_YELLOW + "Váš batoh je prázdný" + "\n" + "Kapacita: " + capacity + Colors.RESET;
        }
        return Colors.BRIGHT_YELLOW + "Váš batoh: " + items + "\n" + "Kapacita: " + capacity + Colors.RESET;
    }
}
