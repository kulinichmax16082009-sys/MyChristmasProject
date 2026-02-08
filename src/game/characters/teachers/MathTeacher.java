package game.characters.teachers;

public class MathTeacher extends Teacher {

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
    public String getJsonFilePath() {
        return "resources/jsonFiles/teachers/mathTeacher.json";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC69\uD83C\uDFFC\u200D\uD83C\uDFEB";
    }
}
