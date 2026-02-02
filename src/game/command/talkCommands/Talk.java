package game.command.talkCommands;

import game.characters.Player;
import game.characters.teachers.Teacher;
import game.command.Command;
import game.inventories.Task;
import game.uiUtils.RandomGenerator;

public class Talk extends Command {
    @Override
    public String execute(Player player, String commandArgument) {
        if (commandArgument != null && !commandArgument.isEmpty()) return "Příkaz 'mluv' nepotřebuje další argumenty";
        if (player.getIsTalking()) return "Nejde provést příkaz, protože hráč už má dialog";
        if (!player.isObjectNearByType(Teacher.class, true, player.getCurrentRoom())) return "Vedle není učitel na dialog";

        player.setIsTalking(true);

        System.out.println(player.getIsTalking());

        Teacher teacher = (Teacher) player.getObjectNearByType(Teacher.class, true, player.getCurrentRoom());

        int tasksCount = player.getRandomTasksCount(new RandomGenerator());

        for (int i = 0; i < tasksCount; i++) {
            Task task = teacher.initializeTask(new RandomGenerator());
            task.startTimer();
            player.addTask(task);
        }

        return teacher.getName() + ": Ahoj " + player.getName() + ", jsem rád/a, že jsi tu." +
                "\n" + teacher.getName() + ": Moje inteligence je " + teacher.getIntelligence() +
                ", takže lehké otázky nečekej!" +
                "\n" + teacher.getName() + ": Přeji ti hodně štěstí a přidávam ti " + tasksCount + " zadání." +
                "\n" + teacher.getName() + ": Zobrazit úkoly můžeš pomocí přikazu <úkoly>";
    }

    @Override
    public boolean exit() {
        return false;
    }
}