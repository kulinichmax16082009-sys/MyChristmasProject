package game.commands.utilsCommands;

import game.characters.Player;
import game.commands.Command;
import game.uiUtils.Colors;
import game.uiUtils.FileManager;

/**
 * This class represents 'napoveda' command, which is used to get hints for current room
 *
 * @author Maksym Kulynych
 */
public class Hint extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        FileManager fileManager = new FileManager();

        if (player.getIsTalking()) return Colors.BRIGHT_YELLOW + fileManager.readAllTxt("resources/txtFiles/hints/talking") + Colors.RESET;

        switch (player.getCurrentRoom().getRoomType()) {
            case CLASSROOM -> {return Colors.BRIGHT_YELLOW + fileManager.readAllTxt("resources/txtFiles/hints/classroom") + Colors.RESET;}
            case TEACHER_OFFICE -> {return Colors.BRIGHT_YELLOW + fileManager.readAllTxt("resources/txtFiles/hints/teacherOffice") + Colors.RESET;}
            case HALL -> {return Colors.BRIGHT_YELLOW + fileManager.readAllTxt("resources/txtFiles/hints/hall") + Colors.RESET;}
        }

        return Colors.BRIGHT_RED + "Nápověda není k dispozici" + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
