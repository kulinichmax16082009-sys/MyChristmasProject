import game.inventories.Inventory;
import game.items.keepable.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for testing the functionality of the Inventory class,
 * specifically checking if the inventory is full after adding items.
 *
 * @author Maksym Kulynych
 */
class InventoryTest {

    @Test
    void inventoryIsFullAfterAddingItems() {
        Inventory inventory = new Inventory();
        inventory.setCapacity(3);

        inventory.addItem(new EnergyDrink());
        inventory.addItem(new Resistor());
        inventory.addItem(new MagicPear());

        assertTrue(inventory.isFull());
    }
}