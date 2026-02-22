package game.characters.teachers;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.characters.Character;
import game.exceptions.BadIntelligenceException;
import game.exceptions.BadTeacherCharacteristicsFormatException;
import game.inventories.Task;
import game.uiUtils.FileManager;
import game.uiUtils.RandomGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * This class represents an abstract class of any teacher in game
 *
 * @author Maksym Kulynych
 */
public abstract class Teacher extends Character {

    private int maxIntelligence;
    private int minIntelligence;
    private float intelligenceModifier;
    private ArrayList<Task> allPossibleTasks;

    public Teacher() {
        super(null);
        allPossibleTasks = new ArrayList<>();
    }

    /**
     * This method is path to .txt file with names
     * @return path to file as String
     */
    public abstract String getNamesFile();

    /**
     * This method is path to .json file with all characteristics
     * @return path to file as String
     */
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

    /**
     * This method initializes Teacher from .json file + name from .txt file
     * @param rnd is used to pick a random name and amount of intelligence
     * @return new initialized teacher that is ready for game
     */
    public Teacher initializeTeacher(RandomGenerator rnd) {
        ObjectMapper mapper = new ObjectMapper();

        Teacher newTeacher;

        try (InputStream input = new FileInputStream(getJsonFilePath())) {
            newTeacher = mapper.readValue(input, (Class<Teacher>) this.getClass());
        } catch (Exception e) {
            throw new BadTeacherCharacteristicsFormatException();
        }

        if (newTeacher.minIntelligence <= 0 || newTeacher.maxIntelligence <= 0 || newTeacher.maxIntelligence <= newTeacher.minIntelligence) {
            throw new BadIntelligenceException();
        }

        newTeacher.setName(initializeName(rnd));
        newTeacher.setIntelligence(rnd.randomNumber(newTeacher.minIntelligence, newTeacher.maxIntelligence));

        return newTeacher;
    }

    /**
     * This method picks a random task from teacher's allPossibleTasks list
     * @param rnd is used for random picking
     * @return new task that was picked
     */
    public Task generateTask(RandomGenerator rnd) {
        int randomIndex = rnd.randomNumber(0, allPossibleTasks.size() - 1);
        return allPossibleTasks.get(randomIndex);
    }

    /**
     * This method initializes name by picking it from .txt file randomly
     * @param rnd is used to generate random line from .txt file
     * @return teacher's name
     */
    public String initializeName(RandomGenerator rnd) {
        FileManager fileManager = new FileManager();
        int lineIndex = rnd.generateFileLineIndex(getNamesFile());

        return fileManager.readLineByIndex(getNamesFile(), lineIndex);
    }

    /**
     * This method represents teacher factory by number
     * @param number number that decides, which type of teacher must be picked
     * @param rnd is used to initialize teacher randomly
     * @return new random initialized teacher
     */
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
