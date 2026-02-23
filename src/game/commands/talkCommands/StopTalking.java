package game.commands.talkCommands;

import game.characters.Player;
import game.characters.teachers.Teacher;
import game.commands.Command;
import game.uiUtils.Colors;

/**
 * This class represents 'ukonci_dialog' command, which is used to stop talking with teacher
 *
 * @author Maksym Kulynych
 */
public class StopTalking extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) {
            return Colors.BRIGHT_RED + "Příkaz 'ukonči_dialog' nepotřebuje další argumenty" + Colors.RESET;
        }
        if (!player.getIsTalking()) return Colors.BRIGHT_RED + "Nejde provést příkaz, protože hráč nezačal dialog" + Colors.RESET;

        player.setIsTalking(false);

        //Pětka za každé nedokončené zadání
        for (int i = 0; i < player.getTasks().size(); i++) {
            player.getMarks().addMark(5);
            player.subIntelligence(500);
        }

        player.getTasks().clear();

        player.removeObjectNearByType(Teacher.class, true, player.getCurrentRoom());

        return Colors.BRIGHT_BLUE + "Hráč ukončil dialog" + Colors.RESET;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
