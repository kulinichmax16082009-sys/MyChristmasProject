package game.items.unkeepable;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.Player;
import game.items.Item;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Chair extends Item {
    public Chair() {
        super(null);
    }

    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
    }

    @Override
    public boolean isKeepable() {
        return false;
    }

    @Override
    public String getPathFile() {
        return "resources/chair.json";
    }

    @Override
    public String getSprite() {
        return "";
    }
}
