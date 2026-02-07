package game.command.utilsCommands;

import game.characters.Player;
import game.command.Command;
import game.uiUtils.FileManager;

public class Hint extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        FileManager fileManager = new FileManager();

        if (player.getIsTalking()) return fileManager.readAllTxt("resources/txtFiles/hints/talking");

        switch (player.getCurrentRoom().getRoomType()) {
            case CLASSROOM -> {return fileManager.readAllTxt("resources/txtFiles/hints/classroom");}
            case TEACHER_OFFICE -> {return fileManager.readAllTxt("resources/txtFiles/hints/teacherOffice");}
            case HALL -> {return fileManager.readAllTxt("resources/txtFiles/hints/hall");}
        }

        return "Nápověda není k dispozici";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
