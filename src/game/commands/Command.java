package game.commands;

import game.characters.Player;

/**
 * This is an abstract class, which represents command, which player can enter in console
 *
 * @author Maksym Kulynych
 */
public abstract class Command {
    /**
     * This method is used to execute command, which player entered in console
     * @param player player, who entered command
     * @param commandArgument argument of command, which player entered in console
     * @return string, which will be printed in console after executing command
     */
    public abstract String execute(Player player, String commandArgument);

    /**
     * This method is used to check, if player entered command, which is used to exit game
     * @return true - player entered command, which is used to exit game, false - otherwise
     */
    public abstract boolean exit();
}
