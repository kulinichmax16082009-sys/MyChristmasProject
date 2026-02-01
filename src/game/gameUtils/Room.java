package game.gameUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private String name;
    @JsonIgnore
    private HashMap<Coordinates, GameObject> gameObjects;
    private int width, height;
    private boolean isVisited;

    public Room(String name, int width, int height) {
        this.name = name;
        this.gameObjects = new HashMap<>();
        this.isVisited = false;

        if (width < 5 && height < 5) {
            this.width = this.height = 5;
        } else {
            this.width = width;
            this.height = height;
        }
    }

    public Room() {
        this.name = "Bez názvu";
        this.gameObjects = new HashMap<>();
        this.width = 3;
        this.height = 3;
        this.isVisited = false;
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

    public boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean visited) {
        isVisited = visited;
    }

//    @Override
//    public String toString() {
//        return "name: " + name +
//                ", gameObjects: " + gameObjects +
//                ", height: " + height +
//                ", width: " + width +
//                ", is visited: " + isVisited;
//    }

    public boolean move(Coordinates where, GameObject gameObject) {
        if (canBePlaced(where) && gameObjects.containsKey(gameObject.getCoordinates())) {
            gameObjects.remove(gameObject.getCoordinates());
            place(where, gameObject);
            gameObject.setCoordinates(where);
            return true;
        }
        return false;
    }

    public boolean place(Coordinates where, GameObject gameObject) {
        if (canBePlaced(where)) {
            gameObjects.put(where, gameObject);
            gameObject.setCoordinates(where);
            return true;
        }
        return false;
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