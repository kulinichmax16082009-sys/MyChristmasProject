package game.items.unkeepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

/**
 * This class represents item 'wall' in game, which is obstacle
 *
 * @author Maksym Kulynych
 */
public class Wall extends Item {

    public Wall() {
        super(null);
    }

    /**
     * This method doesn't do anything, because player can't use it's ability
     * @param player is used to change some characteristics while using item
     * @param rnd is used for random effect from item ability
     */
    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
    }

    @Override
    public boolean isKeepable() {
        return false;
    }

    @Override
    public String getJsonPathFile() {
        return "resources/jsonFiles/items/wall.json";
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDDF1";
    }
}