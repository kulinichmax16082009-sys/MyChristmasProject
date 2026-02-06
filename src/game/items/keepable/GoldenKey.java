package game.items.keepable;

import game.characters.Player;
import game.gameUtils.GameObject;
import game.gameUtils.RoomType;
import game.items.Item;
import game.items.unkeepable.Door;
import game.uiUtils.RandomGenerator;

public class GoldenKey extends Item {
    public GoldenKey() {
        super(null);
    }

    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
        for (GameObject obj : player.getCurrentRoom().getGameObjects().values()) {
            if (obj instanceof Door door) {
                if (!door.getNextRoom().getRoomType().equals(RoomType.MAIN_CLASS)) {
                    door.setConnectedDoorsOpen(true);
                }
            }
        }
    }

    @Override
    public boolean isKeepable() {
        return true;
    }

    @Override
    public String getPathFile() {
        return "resources/items/goldenKey.json";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDD11";
    }
}
