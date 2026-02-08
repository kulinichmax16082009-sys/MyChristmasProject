package game.characters.teachers;

public class EleTeacher extends Teacher {

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachersNames/eleNames";
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
