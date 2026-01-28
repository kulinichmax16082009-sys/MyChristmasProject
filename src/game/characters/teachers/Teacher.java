package game.characters.teachers;

import game.characters.Character;

public abstract class Teacher extends Character {
    public Teacher(String name) {
        super(name);
    }

    public abstract String getTasksFile();
    public abstract String getNamesFile();
    public abstract String getAnswersFile();
    public void initializeTaskAnswerPair() {}
    public static Teacher teacherFactory(int number) {
        return new PhysicsTeacher("");
    }
}
