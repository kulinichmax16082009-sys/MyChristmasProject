package game.characters.teachers;

import game.characters.Character;
import game.inventories.Task;
import game.uiUtils.FileManager;
import game.uiUtils.RandomGenerator;

public abstract class Teacher extends Character {

    public Teacher() {
        super(null);
    }

    public abstract String getQuestionsFile();

    public abstract String getNamesFile();

    public abstract String getAnswersFile();

    public abstract String getDurationsFile();

    public Task generateTask(RandomGenerator rnd) {
        FileManager fileManager = new FileManager();
        int lineIndex = rnd.generateFileLineIndex(getQuestionsFile());

        String question;
        String answer;
        long duration;

        question = fileManager.readLineByIndex(getQuestionsFile(), lineIndex);
        answer = fileManager.readLineByIndex(getAnswersFile(), lineIndex);
        try {
            duration = Long.parseLong(fileManager.readLineByIndex(getDurationsFile(), lineIndex));
        } catch (Exception e) {
            duration = -1;
        }

        return new Task(question, answer, duration);
    }

    public String initializeName(RandomGenerator rnd) {
        FileManager fileManager = new FileManager();
        int lineIndex = rnd.generateFileLineIndex(getNamesFile());

        return fileManager.readLineByIndex(getNamesFile(), lineIndex);
    }

    public static Teacher teacherFactory(int number, RandomGenerator rnd) {
        return switch (number) {
            case 1 -> new CzechTeacher(rnd);
            case 2 -> new PhysicsTeacher(rnd);
            case 3 -> new MathTeacher(rnd);
            case 4 -> new ItTeacher(rnd);
            case 5 -> new EleTeacher(rnd);
            default -> null;
        };
    }
}
