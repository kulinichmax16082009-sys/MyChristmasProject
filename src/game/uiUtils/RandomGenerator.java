package game.uiUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class RandomGenerator {
    private Random rnd = new Random();

    public boolean generateProbability(float percent) {
        float randomChance = rnd.nextFloat(101);
        if (percent > 100 || percent <= 0) return false;
        System.out.println(percent + " + " + randomChance);
        return randomChance <= percent;
    }

    public int generateFileLineIndex(String fileName) {
        int size = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            do size++; while (br.readLine() != null);
        } catch (Exception e) {
            return -1;
        }
        return randomNumber(0, size - 1);
    }

    public int randomNumber(int min, int max) {
        if (max == Integer.MAX_VALUE) return 0;
        return rnd.nextInt(min, max + 1);
    }
}