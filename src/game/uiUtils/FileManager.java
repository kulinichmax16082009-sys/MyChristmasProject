package game.uiUtils;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileManager {

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
