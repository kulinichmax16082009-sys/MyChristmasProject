package game.items.keepable;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.Player;
import game.items.Item;
import game.items.unkeepable.Chair;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;

public class MagicPear extends Item {
    public MagicPear() {
        super(null);
    }

    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
    }

    @Override
    public boolean isKeepable() {
        return true;
    }

    @Override
    public String getPathFile() {
        return "resources/items/magicPear.json";
    }

    @Override
    public String getSprite() {
        return "";
    }
}
