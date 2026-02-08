package game.characters.teachers;

public class EleTeacher extends Teacher {

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
    public String getJsonFilePath() {
        return "resources/jsonFiles/teachers/eleTeacher.json";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC68\uD83C\uDFFB\u200D\uD83C\uDFEB";
    }
}
