package game.gameUtils;
import game.items.Item;

/**
 * This class represents an abstract game object in the room (ex. Player, Teacher, Item, ect.)
 *
 * @author Maksym Kulynych
 */
public abstract class GameObject {
    private String name;
    private Coordinates coordinates;

    public GameObject(String name) {
        this.name = name;
        this.coordinates = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "name: " + name +
                ", coordinates: " + coordinates;
    }

    /**
     * This method is simply emoji of game object in room
     * @return emoji as realisation of object
     */
    public abstract String getSprite();

    /**
     * This method represents all 8 directions around object, which are used to check, if there is some object near another object
     * @return array of all 8 directions
     */
    public int[][] initDirections() {
        return new int[][] { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
    }

    /**
     * This method checks if there is some object of exact class type near another object and if it is keepable or not (if it is item)
     * @param type class type of object, which is checked
     * @param isKeepable if it is true, method will check only for keepable items, if it is false, method will check only for non-keepable items
     * @param currentRoom current room, where method will check for object
     * @return object of exact class type, which is near another object, if there is no such object, method will return null
     */
    public GameObject getObjectNearByType(Class<?> type, boolean isKeepable, Room currentRoom) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        int[][] directions = initDirections();

        for (int[] d : directions) {
            Coordinates coordinates = new Coordinates(x + d[0], y + d[1]);
            if (currentRoom.getGameObjects().containsKey(coordinates)) {

                //Vyběr předmětu
                if (currentRoom.getGameObjects().get(coordinates) instanceof Item item) {
                    if (type.isInstance(item) && item.isKeepable() == isKeepable) return item;
                }

                //Obecný výběr
                else if (type.isInstance(currentRoom.getGameObjects().get(coordinates))) {
                    return currentRoom.getGameObjects().get(coordinates);
                }
            }
        }
        return null;
    }

    /**
     * This method removes object of exact class type, which is near another object, if there is such object
     * @param type class type of object, which is checked
     * @param isKeepable if it is true, method will check only for keepable items, if it is false, method will check only for non-keepable items
     * @param currentRoom current room, where method will remove objects
     */
    public void removeObjectNearByType(Class<?> type, boolean isKeepable, Room currentRoom) {
        if (isObjectNearByType(type, isKeepable, currentRoom)){
            currentRoom.getGameObjects().remove(getObjectNearByType(type, isKeepable, currentRoom).coordinates);
        }
    }

    /**
     * This method checks if there is some object of exact class type near another object and if it is keepable or not (if it is item)
     * @param type class type of object, which is checked
     * @param isKeepable if it is true, method will check only for keepable items, if it is false, method will check only for non-keepable items
     * @param currentRoom current room, where method will check for object
     * @return true - there is some object of exact class type near another object, false - there is no such object
     */
    public boolean isObjectNearByType(Class<?> type, boolean isKeepable, Room currentRoom) {
        return getObjectNearByType(type, isKeepable, currentRoom) != null;
    }
}