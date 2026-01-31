package game.items.keepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class Resistor extends Item {
    public Resistor() {
        super(null);
    }

    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
    }

    @Override
    public boolean isKeepable() {
        return true;
    }

    @Override
    public String getPathFile() {
        return "resources/items/resistor.json";
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDDF5";
    }
}
