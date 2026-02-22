import game.characters.Player;
import game.commands.walkCommands.Walk;
import game.gameUtils.Coordinates;
import game.gameUtils.Room;
import game.gameUtils.RoomType;
import game.uiUtils.Colors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for testing the functionality of the Walk command,
 * specifically checking if the player can move in different directions from the starting position.
 *
 * @author Maksym Kulynych
 */
class WalkTestFromStarterPosition {

    @Test
    void playerCanGoEastByCommand() {
        Room room = new Room("testRoom", 5, 5, RoomType.CLASSROOM);
        Player player = new Player();
        player.setCurrentRoom(room);
        room.place(new Coordinates(0, 0), player);

        Walk walk = new Walk();

        String result = walk.execute(player, "východ");

        assertEquals(Colors.BRIGHT_YELLOW + "Hráč byl posunut na východ" + Colors.RESET, result);
    }

    @Test
    void playerCanGoWestByCommand() {
        Room room = new Room("testRoom", 5, 5, RoomType.CLASSROOM);
        Player player = new Player();
        player.setCurrentRoom(room);
        room.place(new Coordinates(0, 0), player);

        Walk walk = new Walk();
        String result = walk.execute(player, "západ");

        assertEquals(Colors.BRIGHT_RED + "Nedefinované souřadnice" + Colors.RESET, result);
    }

    @Test
    void playerCanGoSouthByCommand() {
        Room room = new Room("testRoom", 5, 5, RoomType.CLASSROOM);
        Player player = new Player();
        player.setCurrentRoom(room);
        room.place(new Coordinates(0, 0), player);

        Walk walk = new Walk();
        String result = walk.execute(player, "jih");

        assertEquals(Colors.BRIGHT_RED + "Nedefinované souřadnice" + Colors.RESET, result);
    }

    @Test
    void playerCanGoNorthByCommand() {
        Room room = new Room("testRoom", 5, 5, RoomType.CLASSROOM);
        Player player = new Player();
        player.setCurrentRoom(room);
        room.place(new Coordinates(0, 0), player);

        Walk walk = new Walk();
        String result = walk.execute(player, "sever");

        assertEquals(Colors.BRIGHT_YELLOW + "Hráč byl posunut na sever" + Colors.RESET, result);
    }
}