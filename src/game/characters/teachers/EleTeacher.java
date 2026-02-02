package game.characters.teachers;

import game.uiUtils.RandomGenerator;

public class EleTeacher extends Teacher {

    public EleTeacher(RandomGenerator rnd) {
        setName(initializeName(rnd));
        setIntelligence(rnd.randomNumber(2500, 4500));
    }

    @Override
    public String getQuestionsFile() {
        return "resources/txtFiles/teachers/ele/eleQuestions";
    }

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachers/ele/eleNames";
    }

    @Override
    public String getAnswersFile() {
        return "resources/txtFiles/teachers/ele/eleAnswers";
    }

    @Override
    public String getDurationsFile() {
        return "resources/txtFiles/teachers/ele/eleDurations";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC68\uD83C\uDFFB\u200D\uD83C\uDFEB";
    }
}
