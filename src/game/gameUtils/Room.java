package game.gameUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private String name;
    private RoomType roomType;
    @JsonIgnore
    private HashMap<Coordinates, GameObject> gameObjects;
    private int width, height;

    public Room(String name, int width, int height, RoomType roomType) {
        this.name = name;
        this.gameObjects = new HashMap<>();
        this.roomType = roomType;

        if (width < 5 && height < 5) {
            this.width = this.height = 5;
        } else {
            this.width = width;
            this.height = height;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Coordinates, GameObject> getGameObjects() {
        return gameObjects;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public boolean move(Coordinates where, GameObject gameObject) {
        if (canBePlaced(where) && gameObjects.containsKey(gameObject.getCoordinates())) {
            gameObjects.remove(gameObject.getCoordinates());
            place(where, gameObject);
            gameObject.setCoordinates(where);
            return true;
        }
        return false;
    }

    public void place(Coordinates where, GameObject gameObject) {
        if (canBePlaced(where)) {
            gameObjects.put(where, gameObject);
            gameObject.setCoordinates(where);
        }
    }

    public boolean canBePlaced(Coordinates where) {
        return where.getX() >= 0 && where.getX() < width &&
                where.getY() >= 0 && where.getY() < height &&
                !gameObjects.containsKey(where);
    }

    public ArrayList<Coordinates> findFreeCoordinates() {
        ArrayList<Coordinates> freeCoordinates = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Coordinates coordinates = new Coordinates(j, i);
                if (!gameObjects.containsKey(coordinates)) {
                    freeCoordinates.add(coordinates);
                }
            }
        }
        return freeCoordinates;
    }

    public Coordinates findRandomFreeCoordinates(RandomGenerator rnd) {
        return findFreeCoordinates().get(rnd.randomNumber(0, findFreeCoordinates().size() - 1));
    }
}