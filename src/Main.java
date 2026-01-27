import game.gameUtils.Game;
import game.items.unkeepable.Chair;

public class Main {
    public static void main(String[] args) {
        Chair chair = new Chair();
        System.out.println(chair.a());

        Game game = new Game();
        game.play();
    }
}