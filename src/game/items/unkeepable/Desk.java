package game.items.unkeepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class Desk extends Item {
    public Desk() {
        super(null);
    }

    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
    }

    @Override
    public boolean isKeepable() {
        return false;
    }

    @Override
    public String getPathFile() {
        return "resources/jsonFiles/items/desk.json";
    }

    @Override
    public String getSprite() {
        return "┬─┬";
    }
}
