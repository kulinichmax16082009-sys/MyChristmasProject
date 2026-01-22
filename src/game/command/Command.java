package game.command;

public abstract class Command {
    public abstract String execute(Player player, String commandArgument);
    public abstract boolean exit();
}
