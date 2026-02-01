package game.items.keepable;

import game.characters.Player;
import game.gameUtils.GameObject;
import game.items.Item;
import game.items.unkeepable.Door;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;

public class EnergyDrink extends Item {
    public EnergyDrink() {
        super(null);
    }

    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
        ArrayList<Item> obstacles = new ArrayList<>();

        for (GameObject obj : player.getCurrentRoom().getGameObjects().values()) {
            if (obj instanceof Item obstacleItem) {
                if (!obstacleItem.isKeepable() && !(obstacleItem instanceof Door)) {
                    obstacles.add(obstacleItem);
                }
            }
        }

        for (Item obstacle : obstacles) player.getCurrentRoom().getGameObjects().remove(obstacle.getCoordinates());

        if (rnd.generateProbability(getDamageChance())){
            player.subIntelligence((int) (player.getIntelligence() * getDamageInPercent()));
        }
    }

    @Override
    public boolean isKeepable() {
        return true;
    }

    @Override
    public String getPathFile() {
        return "resources/items/energyDrink.json";
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDD64";
    }
}