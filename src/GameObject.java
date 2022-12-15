import java.awt.*;

public abstract class GameObject {
   //Protected for at de kan tilgåes af subklasserne
    protected Position position;
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