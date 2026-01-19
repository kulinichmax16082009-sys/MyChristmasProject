import java.util.ArrayList;

public class Room {
    public void move(Coordinates where, GameObject gameObject) {}
    public void place(Coordinates where, GameObject gameObject) {}
    public boolean canBePlaced(Coordinates where) {
        return true;
    }
    public ArrayList<Coordinates> findFreeCoordinates() {
        return new ArrayList<>();
    }
}
