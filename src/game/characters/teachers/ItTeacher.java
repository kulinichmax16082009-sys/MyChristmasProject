package game.characters.teachers;

public class ItTeacher extends Teacher {

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachersNames/itNames";
    }

    @Override
    public String getJsonFilePath() {
        return "resources/jsonFiles/teachers/itTeacher.json";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDCBB";
    }
}
