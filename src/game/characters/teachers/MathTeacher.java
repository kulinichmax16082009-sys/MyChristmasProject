package game.characters.teachers;

public class MathTeacher extends Teacher {
    public MathTeacher(String name) {
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
        return "\uD83D\uDC69\uD83C\uDFFC\u200D\uD83C\uDFEB";
    }
}
