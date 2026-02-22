package game.uiUtils;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class is solving problems with files and reading
 *
 * @author Maksym Kulynych
 */
public class FileManager {

    /**
     * This method converts .txt file into String
     * @param txtPath path to the .txt file
     * @return all .txt file converted into String
     */
    public String readAllTxt(String txtPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(txtPath))) {

            StringBuilder text = new StringBuilder();
            String line;

            while((line = br.readLine()) != null) {
                 text.append(line).append("\n");
            }
            return text.toString();
        } catch (Exception e) {
            return "Soubor je poškozený nebo neexistuje";
        }
    }

    /**
     * This method converts 1 line of .txt file into String by index
     * @param txtPath path to the .txt file
     * @param index index of line, which must be converted
     * @return 1 line of .txt file
     */
    public String readLineByIndex(String txtPath, int index) {
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(txtPath))) {
            for (int i = 0; i < index + 1; i++) {
                result = br.readLine();
            }

        } catch (Exception e) {
            return "Soubor je poškozený nebo neexistuje";
        }
        return result;
    }
}
