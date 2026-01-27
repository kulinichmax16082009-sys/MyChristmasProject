package game.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.Player;
import game.gameUtils.GameObject;
import game.items.unkeepable.Chair;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class Item extends GameObject {
    private int spawnChance;
    private int damageChance;
    private int damageInPercent;

    public Item(String name) {
        super(name);
    }

    public Item() {
        super(null);
        spawnChance = 0;
        damageChance = 0;
        damageInPercent = 0;
    }

    public abstract void useAbility(Player player, RandomGenerator rnd);

    public abstract boolean isKeepable();

    public int getSpawnChance() {
        return spawnChance;
    }

    public void setSpawnChance(int spawnChance) {
        this.spawnChance = spawnChance;
    }

    public int getDamageChance() {
        return damageChance;
    }

    public void setDamageChance(int damageChance) {
        this.damageChance = damageChance;
    }

    public int getDamageInPercent() {
        return damageInPercent;
    }

    public void setDamageInPercent(int damageInPercent) {
        this.damageInPercent = damageInPercent;
    }

    public abstract String getPathFile();

    public abstract Item initializeItem();

    @Override
    public String toString() {
        return "spawn chance: " + spawnChance +
                ", damage chance: " + damageChance +
                ", damage in percent: " + damageInPercent;
    }
}
