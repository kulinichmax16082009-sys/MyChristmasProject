package game.gameUtils;

import game.characters.Player;
import game.uiUtils.FileManager;
import game.uiUtils.OutputUtils;
import game.uiUtils.RandomGenerator;
import game.uiUtils.ScannerUtils;

public class Game {
    WorldGenerator worldGenerator;
    Player player;

    public void play() {
        RoomFactory roomFactory = new RoomFactory();
        ScannerUtils sc = new ScannerUtils();
        OutputUtils ou = new OutputUtils();
        RandomGenerator rnd = new RandomGenerator();
        FileManager fileMgr = new FileManager();

        //Inicialize hráče
        initializeGamePlayer(rnd);

        //inicializace herního světa
        initializeGameWorld(rnd, roomFactory);

        //inicializace prikazu
        sc.initialize();

        //Umíštění hráče na chodbu
        placePlayer();

        //Příběh
        ou.showMessage(fileMgr.readAllTxt("resources/txtFiles/introducingStory"));

        //Hlavní herní smyčka
        gameLoop(sc, ou);
    }

    public boolean isGameEnd(ScannerUtils sc, Player player) {
        return sc.getIsExit() || player.hasNoIntelligence() || player.hasNoRoomsLeft() || player.getMarks().hasEightOnes();
    }

    private void initializeGameWorld(RandomGenerator rnd, RoomFactory roomFactory) {
        worldGenerator = new WorldGenerator();
        worldGenerator.initializeWorld(roomFactory, rnd, player);
        worldGenerator.initializeMainClass(rnd);
        worldGenerator.initializeHall(player, rnd);
        worldGenerator.connectAllRooms(roomFactory, rnd);
    }

    private void initializeGamePlayer(RandomGenerator rnd) {
        player = new Player();
        player.initializePlayer(rnd);
    }

    private void placePlayer() {
        player.setCurrentRoom(worldGenerator.getHall());
        worldGenerator.getHall().place(worldGenerator.getHall().findFreeCoordinates().get(0), player);
    }

    private void gameLoop(ScannerUtils sc, OutputUtils ou) {
        while (!isGameEnd(sc, player)) {
            ou.printRoom(player.getCurrentRoom());
            sc.complete(player, ou);
            player.visitRoom();
        }
    }
}