package game.characters;

import game.gameUtils.GameObject;

/**
 * This class represents an abstract character in the room (ex. Player, Teacher, ect.)
 *
 * @author Maksym Kulynych
 */
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

    /**
     * This method simply adds some amount of intelligence to exact character
     * @param amount amount that must be added
     */
    public void addIntelligence(int amount) {
        intelligence += amount;
    }

    /**
     * This method simply subtract some amount of intelligence from exact character
     * @param amount amount that must be subtracted
     */
    public void subIntelligence(int amount) {
        if (intelligence - amount < 0) intelligence = 0;
        else intelligence -= amount;
    }

    @Override
    public String toString() {
        return "intelligence: " + intelligence;
    }
}
