package game.characters.teachers;

public class CzechTeacher extends Teacher {

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachersNames/czechNames";
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
