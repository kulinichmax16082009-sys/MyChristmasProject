package game.characters;

import game.gameUtils.GameObject;

public abstract class Character extends GameObject {
    public Character(String name) {
        super(name);
    }

    public void addIntelligence(int amount) {}
    public void subIntelligence(int amount) {}
}
