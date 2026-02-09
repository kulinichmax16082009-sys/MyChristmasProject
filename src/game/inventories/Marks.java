package game.inventories;

import game.characters.Player;
import game.uiUtils.Colors;

import java.util.ArrayList;

public class Marks {
    private ArrayList<Integer> marks;

    public Marks() {
        marks = new ArrayList<>();
    }

    public ArrayList<Integer> getMarks() {
        return marks;
    }

    public void addMark(int mark) {
        if (mark < 1 || mark > 5) return;
        marks.add(mark);
    }

    public boolean hasEnoughOnes(Player player) {
        int onesAmount = 0;
        for (Integer mark : marks) if (mark == 1) onesAmount++;
        return onesAmount >= player.getRequiredOnesAmount();
    }

    @Override
    public String toString() {
        if (marks.isEmpty()) {
            return Colors.BRIGHT_YELLOW + "Zatím nemáte žádné známky" + Colors.RESET;
        }
        return Colors.BRIGHT_YELLOW + "Váše známky: " + marks + Colors.RESET;
    }
}
