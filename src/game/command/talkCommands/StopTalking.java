package game.command.talkCommands;

import game.characters.Player;
import game.characters.teachers.Teacher;
import game.command.Command;

public class StopTalking extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) return "Příkaz 'ukonči_dialog' nepotřebuje další argumenty";
        if (!player.getIsTalking()) return "Nejde provést příkaz, protože hráč nezačal dialog";

        player.setIsTalking(false);

        //Pětka za každé nedokončené zadání
        for (int i = 0; i < player.getTasks().size(); i++) player.getMarks().addMark(5);
        player.getTasks().clear();

        player.removeObjectNearByType(Teacher.class, true, player.getCurrentRoom());

        return "Hráč ukončil dialog";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
