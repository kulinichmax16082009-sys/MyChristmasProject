package game.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.Player;
import game.exceptions.*;
import game.gameUtils.GameObject;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * This object is abstract realization of item that will be in the game for player
 *
 * @author Maksym Kulynych
 */
public abstract class Item extends GameObject {
    private String description;
    private float spawnChance;
    private float damageChance;
    private float damageInPercent;
    private int maxCount;

    public Item(String name) {
        super(name);
    }

    /**
     * This method represents ability of exact item that can change game
     * @param player is used to change some characteristics while using item
     * @param rnd is used for random effect from item ability
     */
    public abstract void useAbility(Player player, RandomGenerator rnd);

    /**
     * This method decides if player can pick up item
     * @return true - item is pickable, false - item isn't pickable
     */
    public abstract boolean isKeepable();

    public float getSpawnChance() {
        return spawnChance;
    }

    public float getDamageChance() {
        return damageChance;
    }

    public float getDamageInPercent() {
        return damageInPercent;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxCount() {
        return maxCount;
    }

    /**
     * This method simply subtracts exact amount from item count
     * @param amount amount that must be subtracted
     */
    public void subMaxCount(int amount) {
        if (maxCount - amount >= 0) maxCount -= amount;
    }

    /**
     * This method is simply path to the .json file
     * @return path to the .json file
     */
    public abstract String getJsonPathFile();

    /**
     * This method initializes exact item from .json file
     * @return initialized item
     */
    public Item initializeItem() {
        ObjectMapper mapper = new ObjectMapper();

        Item newItem;

        try (InputStream input = new FileInputStream(getJsonPathFile())) {
            newItem = mapper.readValue(input, (Class<Item>) this.getClass());
        } catch (Exception e) {
            throw new BadItemCharacteristicsFormatException();
        }

        if (newItem.damageInPercent < 0) {
            throw new BadDamageInPercentException();
        }

        if (newItem.maxCount < 0) {
            throw new BadMaxCountException();
        }

        return newItem;
    }

    @Override
    public String toString() {
        return getSprite() + ": " +  description;
    }
}
