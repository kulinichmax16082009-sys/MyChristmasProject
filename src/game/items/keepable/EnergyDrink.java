package game.items.keepable;

import game.characters.Player;
import game.gameUtils.GameObject;
import game.items.Item;
import game.items.unkeepable.Door;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;

/**
 * This class represents item 'energy drink' in game, which is useful item
 *
 * @author Maksym Kulynych
 */
public class EnergyDrink extends Item {
    public EnergyDrink() {
        super(null);
    }

    /**
     * This method allows player use item to clear all obstacles
     * from map, but he can also be damaged by chance
     * @param player is used to change some characteristics while using item
     * @param rnd is used for random effect from item ability
     */
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
    public String getJsonPathFile() {
        return "resources/jsonFiles/items/energyDrink.json";
    }

    @Override
    public String getSprite() {
        return "\uD83E\uDD64";
    }
}