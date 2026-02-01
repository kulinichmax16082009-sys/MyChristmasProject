package game.characters;

import game.gameUtils.GameObject;

public abstract class Character extends GameObject {
    private int intelligence;

    public Character(String name) {
        super(name);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void addIntelligence(int amount) {
        intelligence += amount;
    }

    public void subIntelligence(int amount) {
        if (intelligence - amount < 0) intelligence = 0;
        else intelligence -= amount;
    }

    @Override
    public String toString() {
        return "intelligence: " + intelligence;
    }
}
