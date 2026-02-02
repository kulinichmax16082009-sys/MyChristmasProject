package game.characters.teachers;

import game.uiUtils.RandomGenerator;

public class ItTeacher extends Teacher {

    public ItTeacher(RandomGenerator rnd) {
        setName(initializeName(rnd));
        setIntelligence(rnd.randomNumber(3000, 5000));
    }

    @Override
    public String getQuestionsFile() {
        return "resources/txtFiles/teachers/it/itQuestions";
    }

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachers/it/itNames";
    }

    @Override
    public String getAnswersFile() {
        return "resources/txtFiles/teachers/it/itAnswers";
    }

    @Override
    public String getDurationsFile() {
        return "resources/txtFiles/teachers/it/itDurations";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDCBB";
    }
}
