package game.commands.talkCommands;

import game.characters.Player;
import game.characters.teachers.Teacher;
import game.commands.Command;
import game.uiUtils.Colors;
import game.uiUtils.RandomGenerator;

/**
 * This class represents 'odpověz' command, which is used to answer on teacher's question
 *
 * @author Maksym Kulynych
 */
public class Answer extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (!player.getIsTalking()) return Colors.BRIGHT_RED + "Nejde provést příkaz, protože hráč nezačal dialog" + Colors.RESET;
        if (player.getTasks().isEmpty()) return Colors.BRIGHT_BLUE + "Nemáte žádné zadání, můžete ukončit dialog" + Colors.BRIGHT_YELLOW;

        Teacher nearTeacher = (Teacher) player.getObjectNearByType(Teacher.class, true, player.getCurrentRoom());

        int sameLettersCount = 0;

        String correctAnswer = player.getTasks().get(0).getAnswer().trim().toLowerCase();

        if (!player.getTasks().get(0).isExpired()) {

            int minLength = Math.min(correctAnswer.length(), commandArgument.length());

            for (int j = 0; j < minLength; j++) {
                if (commandArgument.charAt(j) == correctAnswer.charAt(j)) {
                    sameLettersCount++;
                }
            }

            if (sameLettersCount == correctAnswer.length()) {
                player.addIntelligence((int) (player.getIntelligence() * nearTeacher.getIntelligenceModifier()));
                player.getTasks().remove(0);
                player.getMarks().addMark(1);
                return Colors.BRIGHT_BLUE + "Hráč odpověděl na otázku správně" + Colors.RESET;

            } else if (sameLettersCount > correctAnswer.length() / 2 && sameLettersCount < correctAnswer.length()) {
                player.subIntelligence((int) (player.getIntelligence() * nearTeacher.getIntelligenceModifier()));
                player.getTasks().remove(0);
                player.getMarks().addMark(new RandomGenerator().randomNumber(2,3));
                return Colors.BRIGHT_BLUE + "Hráč odpověděl na otázku nepřesně" + Colors.RESET;

            } else if (sameLettersCount <= correctAnswer.length() / 2) {
                player.subIntelligence((int) (nearTeacher.getIntelligence() * nearTeacher.getIntelligenceModifier()));
                player.getTasks().remove(0);
                player.getMarks().addMark(new RandomGenerator().randomNumber(4,5));
                return Colors.BRIGHT_BLUE + "Hráč odpověděl na otázku nedostatečně" + Colors.RESET;
            }
        }

        player.getMarks().addMark(5);
        player.subIntelligence((int) (nearTeacher.getIntelligence() * nearTeacher.getIntelligenceModifier()));
        player.getTasks().remove(0);
        return Colors.BRIGHT_BLUE + "Hráč nestihl odpovědět na otázku" + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
