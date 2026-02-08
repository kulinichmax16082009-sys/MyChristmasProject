package game.gameUtils;

import game.characters.Player;
import game.uiUtils.*;

public class Game {
    WorldGenerator worldGenerator;
    Player player;

    public Game() {
        this.worldGenerator = new WorldGenerator();
        this.player = new Player();
    }

    public void play() {
        RoomFactory roomFactory = new RoomFactory();
        ScannerUtils sc = new ScannerUtils();
        OutputUtils ou = new OutputUtils();
        RandomGenerator rnd = new RandomGenerator();
        FileManager fileMgr = new FileManager();

        //Inicialize hráče
        player.initializePlayer();

        //inicializace herního světa
        worldGenerator.initializeWorld(roomFactory, rnd);

        //inicializace prikazu
        sc.initialize();

        //Umíštění hráče na chodbu
        placePlayer();

        //Příběh
        ou.showMessage(fileMgr.readAllTxt("resources/txtFiles/mainStories/introducingStory"));

        //Hlavní herní smyčka
        gameLoop(sc, ou, fileMgr);
    }

    private void placePlayer() {
        player.setCurrentRoom(worldGenerator.getHall());
        worldGenerator.getHall().place(worldGenerator.getHall().findFreeCoordinates().get(0), player);
    }

    private void gameLoop(ScannerUtils sc, OutputUtils ou, FileManager fileMgr) {
        while (!sc.getIsExit()) {
            ou.printRoom(player.getCurrentRoom());
            sc.complete(player, ou);

            //Všechny konce hry
            if (player.hasNoIntelligence()) {
                ou.showMessage("Hráčovi došla inteligence! Program končí...");
                System.exit(0);
            }

            if (!player.getMarks().hasEnoughOnes(player) && !worldGenerator.isAnyTeacherLeft()) {
                ou.showMessage("Hráčovi došel počet zbývajících místnosti! Program končí...");
                System.exit(0);
            }

            if (player.getCurrentRoom().getRoomType().equals(RoomType.MAIN_CLASS)) {
                fileMgr.readAllTxt("resources/txtFiles/mainStories/endingStory");
                System.exit(0);
            }

            if (player.getMarks().hasEnoughOnes(player)) {
                ou.showMessage("Hráč dostal " + player.getRequiredOnesAmount() + " jedníček a může jit do Učebny č.1");
                worldGenerator.openMainClass();
            }
        }
    }
}