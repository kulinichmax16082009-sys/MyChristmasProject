package game.gameUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private String name;
    private HashMap<Coordinates, GameObject> gameObjects;
    private int width, height;
    private boolean isVisited;

    public Room(String name, int width, int height) {
        this.name = name;
        this.gameObjects = new HashMap<>();
        this.width = width;
        this.height = height;
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

    public void setGameObjects(HashMap<Coordinates, GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        return "name: " + name +
                ", gameObjects: " + gameObjects +
                ", height: " + height +
                ", width: " + width +
                ", is visited: " + isVisited;
    }


    //TODO: Dodelat implementace metody
    public void move(Coordinates where, GameObject gameObject) {}
    public void place(Coordinates where, GameObject gameObject) {}
    public boolean canBePlaced(Coordinates where) {
        return true;
    }
    public ArrayList<Coordinates> findFreeCoordinates() {
        return new ArrayList<>();
    }
}
