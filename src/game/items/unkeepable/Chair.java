package game.items.unkeepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class Chair extends Item {
    public Chair() {
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
        return "resources/jsonFiles/items/chair.json";
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDE91";
    }
}
