package game.characters.teachers;

import game.uiUtils.RandomGenerator;

public class MathTeacher extends Teacher {

    public MathTeacher(RandomGenerator rnd) {
        setName(initializeName(rnd));
        setIntelligence(rnd.randomNumber(2000, 3000));
    }

    @Override
    public String getQuestionsFile() {
        return "resources/txtFiles/teachers/math/mathQuestions";
    }

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachers/math/mathNames";
    }

    @Override
    public String getAnswersFile() {
        return "resources/txtFiles/teachers/math/mathAnswers";
    }

    @Override
    public String getDurationsFile() {
        return "resources/txtFiles/teachers/math/mathDurations";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC69\uD83C\uDFFC\u200D\uD83C\uDFEB";
    }
}
