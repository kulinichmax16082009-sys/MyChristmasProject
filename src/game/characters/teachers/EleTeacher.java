package game.characters.teachers;

public class EleTeacher extends Teacher {
    public EleTeacher(String name) {
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
        return "\uD83D\uDC68\uD83C\uDFFB\u200D\uD83C\uDFEB";
    }
}
