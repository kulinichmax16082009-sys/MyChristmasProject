package game.uiUtils;

import game.characters.Player;
import game.commands.Command;
import game.commands.inventoryCommands.*;
import game.commands.showCommands.*;
import game.commands.talkCommands.*;
import game.commands.utilsCommands.*;
import game.commands.walkCommands.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is solving problems with player's input in console
 *
 * @author Maksym Kulynych
 */
public class ScannerUtils {
    private HashMap<String, Command> commands = new HashMap<>();
    private boolean isExit = false;
    private Scanner scanner = new Scanner(System.in);

    /**
     * This method initializes all commands in game and puts them in HashMap with their name as key
     */
    public void initialize() {
        commands.put("jdi", new Walk());
        commands.put("vstup", new Entry());

        commands.put("inventář", new ShowInventory());
        commands.put("známky", new ShowMarks());
        commands.put("úkoly", new ShowTasks());
        commands.put("inteligence", new ShowIntelligence());

        commands.put("konec", new Quit());
        commands.put("pomoc", new Help());
        commands.put("nápověda", new Hint());

        commands.put("seber", new Collect());
        commands.put("vyhod", new Drop());
        commands.put("použíj", new Use());

        commands.put("mluv", new Talk());
        commands.put("odpověz", new Answer());
        commands.put("ukonči_dialog", new StopTalking());
    }

    /**
     * This method executes exact command by player's input. It also ckecks if command is valid
     * @param player is used to change characteristics by some commands
     * @param ou is used to print current state of the command
     */
    public void complete(Player player, OutputUtils ou) {
        ou.showMessage(Colors.BRIGHT_CYAN + ">> " + Colors.RESET);
        String command  = scanner.nextLine().trim().toLowerCase();

        String basicCommand, argument = "";

        String[] commandSplited = {""};

        if (!command.isEmpty()) commandSplited = command.split(" ");

        basicCommand = commandSplited[0];

        if (commandSplited.length > 1) argument = commandSplited[1];

        if (commands.containsKey(basicCommand) && commandSplited.length <= 2) {
            ou.showMessage(commands.get(basicCommand).execute(player, argument));
            isExit = commands.get(basicCommand).exit();
        } else ou.showMessage(Colors.BRIGHT_RED + ">> Nedefinovaný příkaz" + Colors.RESET);
    }

    public boolean getIsExit() {
        return isExit;
    }
}