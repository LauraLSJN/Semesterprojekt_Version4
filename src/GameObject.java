import java.awt.*;

public abstract class GameObject {
    public Position position;
    protected Size size;
    protected Farve randomColor;
    protected Price price;

    public GameObject(){
        size = new Size();
        randomColor = new Farve();
        price = new Price();
    }

    public abstract Image getSprite();
    public abstract void update();
    public Position getPosition() {
        return position;
    }
    public Price getPrice(){return price;}

}