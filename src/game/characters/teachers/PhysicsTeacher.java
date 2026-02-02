package game.characters.teachers;

import game.uiUtils.RandomGenerator;

public class PhysicsTeacher extends Teacher {

    public PhysicsTeacher(RandomGenerator rnd) {
        setName(initializeName(rnd));
        setIntelligence(rnd.randomNumber(2500, 4000));
    }

    @Override
    public String getQuestionsFile() {
        return "resources/txtFiles/teachers/physics/physicsQuestions";
    }

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachers/physics/physicsNames";
    }

    @Override
    public String getAnswersFile() {
        return "resources/txtFiles/teachers/physics/physicsAnswers";
    }

    @Override
    public String getDurationsFile() {
        return "resources/txtFiles/teachers/physics/physicsDurations";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDCBC";
    }
}
