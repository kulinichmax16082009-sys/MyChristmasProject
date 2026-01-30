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

    public int[][] initializeDirections() {
        return new int[][] { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
    }

    public GameObject getAnyObjectNearByType(Class<?> type, boolean isKeepable, Room currentRoom) {
        int x = getCoordinates().getX();
        int y = getCoordinates().getY();

        for (int[] d : initializeDirections()) {
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

    public boolean isAnyObjectNearByType(Class<?> type, boolean isKeepable, Room currentRoom) {
        return getAnyObjectNearByType(type, isKeepable, currentRoom) != null;
    }
}