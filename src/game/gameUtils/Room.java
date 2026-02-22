package game.gameUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.uiUtils.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents one room in the game world. It contains all game objects in it and methods to move them around
 *
 * @author Maksym Kulynych
 */
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

    /**
     * This method moves game object from one coordinates to another coordinates in the same room
     * @param where new coordinates, where game object will be moved
     * @param gameObject game object, which will be moved
     * @return true if game object was successfully moved, false if it was not possible to move game object to new coordinates
     */
    public boolean move(Coordinates where, GameObject gameObject) {
        if (canBePlaced(where) && gameObjects.containsKey(gameObject.getCoordinates())) {
            gameObjects.remove(gameObject.getCoordinates());
            place(where, gameObject);
            gameObject.setCoordinates(where);
            return true;
        }
        return false;
    }

    /** This method places game object on exact coordinates in the room
     * @param where coordinates, where game object will be placed
     * @param gameObject game object, which will be placed
     */
    public void place(Coordinates where, GameObject gameObject) {
        if (canBePlaced(where)) {
            gameObjects.put(where, gameObject);
            gameObject.setCoordinates(where);
        }
    }

    /** This method checks if game object can be placed on exact coordinates in the room
     * @param where coordinates, where game object will be placed
     * @return true - game object can be placed on exact coordinates, false - cannot be placed on exact coordinates
     */
    public boolean canBePlaced(Coordinates where) {
        return where.getX() >= 0 && where.getX() < width &&
                where.getY() >= 0 && where.getY() < height &&
                !gameObjects.containsKey(where);
    }

    /** This method finds all free coordinates in the room, where game object can be placed
     * @return list of all free coordinates in the room
     */
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

    /** This method finds random free coordinates in the room, where game object can be placed
     * @param rnd is used to generate random index of free coordinates list
     * @return random free coordinates in the room
     */
    public Coordinates findRandomFreeCoordinates(RandomGenerator rnd) {
        return findFreeCoordinates().get(rnd.randomNumber(0, findFreeCoordinates().size() - 1));
    }
}