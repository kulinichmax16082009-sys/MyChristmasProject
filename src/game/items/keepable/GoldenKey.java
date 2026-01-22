package game.items.keepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class GoldenKey extends Item {
    public GoldenKey(String name) {
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
