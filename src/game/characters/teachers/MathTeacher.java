package game.characters.teachers;

public class MathTeacher extends Teacher {

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachersNames/mathNames";
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
