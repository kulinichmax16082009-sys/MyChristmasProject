package game.items.keepable;

import game.characters.Player;
import game.characters.teachers.Teacher;
import game.gameUtils.Coordinates;
import game.gameUtils.GameObject;
import game.items.Item;
import game.uiUtils.RandomGenerator;

/**
 * This class represents item 'magic pear' in game, which is useful item
 *
 * @author Maksym Kulynych
 */
public class MagicPear extends Item {
    public MagicPear() {
        super(null);
    }

    /**
     * This method allows player to clear nearest teacher from map and add 1 to
     * his marks, but he can be damaged by chance
     * @param player is used to change some characteristics while using item
     * @param rnd is used for random effect from item ability
     */
    @Override
    public void useAbility(Player player, RandomGenerator rnd) {
        Coordinates teacherCoords = null;

        for (GameObject obj : player.getCurrentRoom().getGameObjects().values()) {
            if (obj instanceof Teacher teacher) {
                teacherCoords = teacher.getCoordinates();
            }
        }

        if (teacherCoords == null) return;

        player.getCurrentRoom().getGameObjects().remove(teacherCoords);
        player.getMarks().addMark(1);

        if (rnd.generateProbability(getDamageChance())) {
            player.subIntelligence((int) (player.getIntelligence() * getDamageInPercent()));
        }
    }

    @Override
    public boolean isKeepable() {
        return true;
    }

    @Override
    public String getJsonPathFile() {
        return "resources/jsonFiles/items/magicPear.json";
    }

    @Override
    public String getSprite() {
        return "\uD83C\uDF50";
    }
}
