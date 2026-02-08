package game.items.keepable;

import game.characters.Player;
import game.characters.teachers.Teacher;
import game.gameUtils.Coordinates;
import game.gameUtils.GameObject;
import game.items.Item;
import game.uiUtils.RandomGenerator;

public class MagicPear extends Item {
    public MagicPear() {
        super(null);
    }

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
    public String getPathFile() {
        return "resources/jsonFiles/items/magicPear.json";
    }

    @Override
    public String getSprite() {
        return "\uD83C\uDF50";
    }
}
