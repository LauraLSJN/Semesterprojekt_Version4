import java.awt.*;

public abstract class GameObject {
    public Position position;
    protected Size size;
    protected ColorInGame color;
    protected Price price;

    public GameObject(){
        size = new Size();
        color = new ColorInGame();
        price = new Price();
    }

    public abstract Image getSprite();
    public abstract void updateGameObject();
    public Position getPosition() {
        return position;
    }

    public Price getPrice(){
        return price;
    }

}