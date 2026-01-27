package game.items.keepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class EnergyDrink extends Item {
    public EnergyDrink(String name) {
        super(name);
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
