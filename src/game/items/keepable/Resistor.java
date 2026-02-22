package game.items.keepable;

import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

/**
 * This class represents item 'resistor' in game, which is useful item
 *
 * @author Maksym Kulynych
 */
public class Resistor extends Item {
    public Resistor() {
        super(null);
    }

    /**
     * This method allows player to simply increase duration
     * of all tasks 2x, but he can be damaged by chance
     * @param player is used to change some characteristics while using item
     * @param rnd is used for random effect from item ability
     */
    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
        for (int i = 0; i < player.getTasks().size(); i++) {
            player.getTasks().get(i).addDuration(player.getTasks().get(i).getDuration());
        }

        if (rnd.generateProbability(getDamageChance())) {
            player.subIntelligence((int) (player.getIntelligence() * getDamageInPercent()));
        }
    }

    @Override
    public boolean isKeepable() {
        return true;
    }

    @Override
    public String getJsonPathFile() {
        return "resources/jsonFiles/items/resistor.json";
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDDF5";
    }
}
