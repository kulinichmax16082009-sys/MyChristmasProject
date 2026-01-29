package game.gameUtils;

import game.characters.teachers.Teacher;
import game.items.Item;
import game.items.unkeepable.Door;

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

    //TODO: Dodelat metody pro hledani objektu v okoli
    public abstract String getSprite();

    public int[][] initializeDirections() {
        return new int[][] { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
    }

    public Item getAnyItemNear(boolean isKeepable, Room currentRoom) {
        int x = getCoordinates().getX();
        int y = getCoordinates().getY();

        for (int[] d : initializeDirections()) {
            Coordinates coordinates = new Coordinates(x + d[0], y + d[1]);
            if (currentRoom.getGameObjects().containsKey(coordinates)) {
                if (currentRoom.getGameObjects().get(coordinates) instanceof Item item) {
                    if (item.isKeepable() == isKeepable) return item;
                }
            }
        }
        return null;
    }

    public Teacher getAnyTeacherNear(Room currentRoom) {
        int x = getCoordinates().getX();
        int y = getCoordinates().getY();

        for (int[] d : initializeDirections()) {
            Coordinates coordinates = new Coordinates(x + d[0], y + d[1]);
            if (currentRoom.getGameObjects().containsKey(coordinates)) {
                if (currentRoom.getGameObjects().get(coordinates) instanceof Teacher teacher) {
                    return teacher;
                }
            }
        }
        return null;
    }

    public Door getAnyDoorNear(Room currentRoom) {
        int x = getCoordinates().getX();
        int y = getCoordinates().getY();

        for (int[] d : initializeDirections()) {
            Coordinates coordinates = new Coordinates(x + d[0], y + d[1]);
            if (currentRoom.getGameObjects().containsKey(coordinates)) {
                if (currentRoom.getGameObjects().get(coordinates) instanceof Door door) {
                    return door;
                }
            }
        }
        return null;
    }

    public boolean isAnyItemNear(boolean isKeepable, Room currentRoom) {
        return getAnyItemNear(isKeepable, currentRoom) != null;
    }

    public boolean isAnyTeacherNear(Room currentRoom) {
        return getAnyTeacherNear(currentRoom) != null;
    }

    public boolean isAnyDoorNear(Room currentRoom) {
        return getAnyDoorNear(currentRoom) != null;
    }
}