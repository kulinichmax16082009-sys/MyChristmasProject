package game.characters.teachers;

import game.characters.Character;

public abstract class Teacher extends Character {
    public abstract String getTasksFile();
    public abstract String getNamesFile();
    public abstract String getAnswersFile();
    public void initializeTaskAnswerPair() {}
    public Teacher teacherFactory(int number) {
        return null;
    }
}
