package game.items;

import game.characters.Player;
import game.gameUtils.GameObject;
import game.uiUtils.RandomGenerator;

public abstract class Item extends GameObject {
    public abstract void useAbility(Player player, RandomGenerator rnd);
    public abstract boolean isKeepable();
}
