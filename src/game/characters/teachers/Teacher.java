package game.characters.teachers;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.Character;
import game.exceptions.BadIntelligenceException;
import game.inventories.Task;
import game.uiUtils.FileManager;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class Teacher extends Character {

    private int maxIntelligence;
    private int minIntelligence;
    private float intelligenceModifier;
    private ArrayList<Task> allPossibleTasks;

    public Teacher() {
        super(null);
        allPossibleTasks = new ArrayList<>();
    }

    public abstract String getNamesFile();

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

    public ArrayList<Task> getAllPossibleTasks() {
        return allPossibleTasks;
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
        int randomIndex = rnd.randomNumber(0, allPossibleTasks.size() - 1);
        return allPossibleTasks.get(randomIndex);
    }

    public String initializeName(RandomGenerator rnd) {
        FileManager fileManager = new FileManager();
        int lineIndex = rnd.generateFileLineIndex(getNamesFile());

        return fileManager.readLineByIndex(getNamesFile(), lineIndex);
    }

    public static Teacher teacherFactory(int number, RandomGenerator rnd) {
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
