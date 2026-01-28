package game.characters.teachers;

public class ItTeacher extends Teacher {
    public ItTeacher(String name) {
        super(name);
    }

    @Override
    public String getTasksFile() {
        return "";
    }

    @Override
    public String getNamesFile() {
        return "";
    }

    @Override
    public String getAnswersFile() {
        return "";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDCBB";
    }
}
