package game.items.unkeepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class Wall extends Item {
    public Wall(String name) {
        super(name);
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
        return "";
    }

    @Override
    public Item initializeItem() {
        return null;
    }

    @Override
    public String getSprite() {
        return "";
    }
}
