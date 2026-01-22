package game.items;

public abstract class Item extends GameObject {
    public abstract void useAbility(Player player, RandomGenerator rnd);
    public abstract boolean isKeepable();
}
