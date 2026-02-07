package game.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.Player;
import game.gameUtils.GameObject;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;

public abstract class Item extends GameObject {
    private String description;
    private float spawnChance;
    private float damageChance;
    private float damageInPercent;
    private int maxCount;

    public Item(String name) {
        super(name);
    }

    public abstract void useAbility(Player player, RandomGenerator rnd);

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

    public void subMaxCount(int amount) {
        if (maxCount - amount >= 0) maxCount -= amount;
    }

    public abstract String getPathFile();

    public Item initializeItem() {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream input = new FileInputStream(getPathFile())) {
            return mapper.readValue(input, (Class<Item>) this.getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return getSprite() + ": " +  description;
    }
}
