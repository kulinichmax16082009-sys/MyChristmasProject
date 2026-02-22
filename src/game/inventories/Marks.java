package game.inventories;

import game.characters.Player;

import java.util.ArrayList;

/**
 * This class represents player's inventory of marks
 *
 * @author Maksym Kulynych
 */
public class Marks {
    private ArrayList<Integer> marks;

    public Marks() {
        marks = new ArrayList<>();
    }

    public ArrayList<Integer> getMarks() {
        return marks;
    }

    /**
     * This method simply adds one mark to player's marks
     * @param mark mark in range of 1 to 5 that must be put into player's marks
     */
    public void addMark(int mark) {
        if (mark < 1 || mark > 5) return;
        marks.add(mark);
    }

    /**
     * This method checks if player gained enough ones by checking his characteristics
     * @param player player whose marks are being checked
     * @return true - player has enough ones, false - otherwise
     */
    public boolean hasEnoughOnes(Player player) {
        int onesAmount = 0;
        for (Integer mark : marks) if (mark == 1) onesAmount++;
        return onesAmount >= player.getRequiredOnesAmount();
    }

    /**
     * This method is player's marks converted into String
     * @return all player's marks
     */
    @Override
    public String toString() {
        if (marks.isEmpty()) return "Zatím nemáte žádné známky";
        return "Váše známky: " + marks;
    }
}
