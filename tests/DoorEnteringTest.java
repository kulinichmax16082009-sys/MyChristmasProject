import game.characters.Player;
import game.gameUtils.Coordinates;
import game.gameUtils.Room;
import game.items.unkeepable.Door;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for testing the functionality of entering a door and transitioning to the next room.
 *
 * @author Maksym Kulynych
 */
class DoorEnteringTest {

    @Test
    void playerWillGoThroughTheDoor() {
        Room currentRoom = new Room("currentRoom", 5, 5, null);
        Room nextRoom = new Room("nextRoom", 5, 5, null);

        Door currentDoor = new Door();
        Door nextDoor = new Door();

        currentRoom.place(new Coordinates(0, 0), currentDoor);
        nextRoom.place(new Coordinates(0, 0), nextDoor);

        currentDoor.setNextDoor(nextDoor);
        currentDoor.setNextRoom(nextRoom);

        nextDoor.setNextDoor(currentDoor);
        nextDoor.setNextRoom(currentRoom);

        Player player = new Player();

        player.setCurrentRoom(currentRoom);
        currentRoom.place(new Coordinates(0, 1), player);

        currentDoor.useAbility(player, null);

        assertEquals(nextRoom, player.getCurrentRoom());
    }
}