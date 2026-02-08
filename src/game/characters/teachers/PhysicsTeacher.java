package game.characters.teachers;

public class PhysicsTeacher extends Teacher {

    @Override
    public String getNamesFile() {
        return "resources/txtFiles/teachersNames/physicsNames";
    }

    @Override
    public String getJsonFilePath() {
        return "resources/jsonFiles/teachers/physicsTeacher.json";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDCBC";
    }
}
