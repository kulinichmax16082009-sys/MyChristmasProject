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

    public void setSpawnChance(float spawnChance) {
        this.spawnChance = spawnChance;
    }

    public float getDamageChance() {
        return damageChance;
    }

    public void setDamageChance(float damageChance) {
        this.damageChance = damageChance;
    }

    public float getDamageInPercent() {
        return damageInPercent;
    }

    public void setDamageInPercent(float damageInPercent) {
        this.damageInPercent = damageInPercent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
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
        return "Item{" +
                "description='" + description + '\'' +
                ", spawnChance=" + spawnChance +
                ", damageChance=" + damageChance +
                ", damageInPercent=" + damageInPercent +
                ", maxCount=" + maxCount +
                '}';
    }
}
