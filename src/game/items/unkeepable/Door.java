package game.items.unkeepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class Door extends Item {
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
