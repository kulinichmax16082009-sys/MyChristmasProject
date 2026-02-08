package game.characters.teachers;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.Character;
import game.exceptions.BadIntelligenceException;
import game.inventories.Task;
import game.uiUtils.FileManager;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;

public abstract class Teacher extends Character {

    private int maxIntelligence;
    private int minIntelligence;
    private float intelligenceModifier;

    public Teacher() {
        super(null);
    }

    public abstract String getQuestionsFile();

    public abstract String getNamesFile();

    public abstract String getAnswersFile();

    public abstract String getDurationsFile();

    public abstract String getJsonFilePath();

    public int getMaxIntelligence() {
        return maxIntelligence;
    }

    public int getMinIntelligence() {
        return minIntelligence;
    }

    public float getIntelligenceModifier() {
        return intelligenceModifier;
    }

    public Teacher initializeTeacher(RandomGenerator rnd) {
        ObjectMapper mapper = new ObjectMapper();

        Teacher newTeacher;

        try (InputStream input = new FileInputStream(getJsonFilePath())) {
            newTeacher = mapper.readValue(input, (Class<Teacher>) this.getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (newTeacher.minIntelligence <= 0 || newTeacher.maxIntelligence <= 0 || newTeacher.maxIntelligence <= newTeacher.minIntelligence) {
            throw new BadIntelligenceException();
        }

        newTeacher.setName(initializeName(rnd));
        newTeacher.setIntelligence(rnd.randomNumber(newTeacher.minIntelligence, newTeacher.maxIntelligence));

        return newTeacher;
    }

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
//        return new ItTeacher().initializeTeacher(rnd);
        return switch (number) {
            case 1 -> new CzechTeacher().initializeTeacher(rnd);
            case 2 -> new PhysicsTeacher().initializeTeacher(rnd);
            case 3 -> new MathTeacher().initializeTeacher(rnd);
            case 4 -> new ItTeacher().initializeTeacher(rnd);
            case 5 -> new EleTeacher().initializeTeacher(rnd);
            default -> null;
        };
    }
}
