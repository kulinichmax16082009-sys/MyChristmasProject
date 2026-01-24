package game.gameUtils;

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

    public GameObject getNearObjectByType(Class<?> type, boolean isKeepable) {
        return null;
    }

    public boolean isAnyObjectNearByType(Class<?> type, boolean isKeepable) {
        return false;
    }
}
