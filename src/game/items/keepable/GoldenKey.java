package game.items.keepable;

import game.characters.Player;
import game.gameUtils.GameObject;
import game.gameUtils.RoomType;
import game.items.Item;
import game.items.unkeepable.Door;
import game.uiUtils.RandomGenerator;

/**
 * This class represents item 'golden key' in game, which is useful item
 *
 * @author Maksym Kulynych
 */
public class GoldenKey extends Item {
    public GoldenKey() {
        super(null);
    }

    /**
     * This method allows player to unlock all doors in a room except the main class door
     * @param player is used to change some characteristics while using item
     * @param rnd is used for random effect from item ability
     */
    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
        for (GameObject obj : player.getCurrentRoom().getGameObjects().values()) {
            if (obj instanceof Door door) {
                if (!door.getNextRoom().getRoomType().equals(RoomType.MAIN_CLASS)) {
                    door.setConnectedDoorsOpen(true);
                }
            }
        }
    }

    @Override
    public boolean isKeepable() {
        return true;
    }

    @Override
    public String getJsonPathFile() {
        return "resources/jsonFiles/items/goldenKey.json";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDD11";
    }
}
