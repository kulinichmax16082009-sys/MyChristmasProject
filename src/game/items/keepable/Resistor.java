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
    public String getPathFile() {
        return "resources/jsonFiles/items/resistor.json";
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDDF5";
    }
}
