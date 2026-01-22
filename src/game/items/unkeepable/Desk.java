package game.items.unkeepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class Desk extends Item {
    public Desk(String name) {
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
    public String getSprite() {
        return "";
    }
}
