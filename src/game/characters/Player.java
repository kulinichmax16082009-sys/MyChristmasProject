package game.characters;

public class Player extends Character {
    public Player(String name) {
        super(name);
    }

    @Override
    public String getSprite() {
        return "";
    }

    public void subRoomsLeftCount(int amount) {}
}
