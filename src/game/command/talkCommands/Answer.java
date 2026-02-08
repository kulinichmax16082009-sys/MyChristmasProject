package game.command.talkCommands;

import game.characters.Player;
import game.characters.teachers.Teacher;
import game.command.Command;
import game.uiUtils.RandomGenerator;

public class Answer extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (!player.getIsTalking()) return "Nejde provést příkaz, protože hráč nezačal dialog";
        if (player.getTasks().isEmpty()) return "Nemáte žádné zadání, můžete ukončit dialog";

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
                return "Hráč odpověděl na otázku správně";

            } else if (sameLettersCount > correctAnswer.length() / 2 && sameLettersCount < correctAnswer.length()) {
                player.subIntelligence((int) (player.getIntelligence() * nearTeacher.getIntelligenceModifier()));
                player.getTasks().remove(0);
                player.getMarks().addMark(new RandomGenerator().randomNumber(2,3));
                return "Hráč odpověděl na otázku nepřesně";

            } else if (sameLettersCount <= correctAnswer.length() / 2) {
                player.subIntelligence((int) (nearTeacher.getIntelligence() * nearTeacher.getIntelligenceModifier()));
                player.getTasks().remove(0);
                player.getMarks().addMark(new RandomGenerator().randomNumber(4,5));
                return "Hráč odpověděl na otázku nedostatečně";
            }
        }

        player.getMarks().addMark(5);
        player.subIntelligence((int) (nearTeacher.getIntelligence() * nearTeacher.getIntelligenceModifier()));
        player.getTasks().remove(0);
        return "Hráč nestihl odpovědět na otázku";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
