package game.gameUtils;
import game.items.Item;

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

    public abstract String getSprite();

    public int[][] initDirections() {
        return new int[][] { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
    }

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
                if (type.isInstance(currentRoom.getGameObjects().get(coordinates))) {
                    return currentRoom.getGameObjects().get(coordinates);
                }
            }
        }
        return null;
    }

    public void removeObjectNearByType(Class<?> type, boolean isKeepable, Room currentRoom) {
        if (isObjectNearByType(type, isKeepable, currentRoom)){
            currentRoom.getGameObjects().remove(getObjectNearByType(type, isKeepable, currentRoom).coordinates);
        }
    }

    public boolean isObjectNearByType(Class<?> type, boolean isKeepable, Room currentRoom) {
        return getObjectNearByType(type, isKeepable, currentRoom) != null;
    }
}