package game.gameUtils;

public abstract class GameObject {
    public abstract String getSprite();
    public GameObject getNearObjectByType(Class<?> type, boolean isKeepable) {
        return null;
    }
}
