package game.characters.teachers;

public class CzechTeacher extends Teacher {

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
    public String getJsonFilePath() {
        return "resources/jsonFiles/teachers/czechTeacher.json";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC69\uD83C\uDFFB\u200D\uD83C\uDFEB";
    }
}
