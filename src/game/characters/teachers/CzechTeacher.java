package game.characters.teachers;

import game.uiUtils.RandomGenerator;

public class CzechTeacher extends Teacher {

    public CzechTeacher(RandomGenerator rnd) {
        setName(initializeName(rnd));
        setIntelligence(rnd.randomNumber(2000, 2500));
    }

    @Override
    public String getQuestionsFile() {
        return "resources/txtFiles/teachers/czech/czechQuestions";
    }

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachers/czech/czechNames";
    }

    @Override
    public String getAnswersFile() {
        return "resources/txtFiles/teachers/czech/czechAnswers";
    }

    @Override
    public String getDurationsFile() {
        return "resources/txtFiles/teachers/czech/czechDurations";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC69\uD83C\uDFFB\u200D\uD83C\uDFEB";
    }
}
