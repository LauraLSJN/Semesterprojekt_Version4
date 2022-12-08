
import java.awt.Color;

//Klasse til at genrer random farve
public class Farve {
    public Color randomColor;
    public Color minusFarve;
    public Color plusFarve;

    public Farve() {

        this.randomColor = setRandomColor();
        this.minusFarve = Color.red;
        this.plusFarve = Color.green;
    }

    public Color setRandomColor() {
        float red   = (float) Math.random();
        float green = (float) Math.random();
        float blue  = (float) Math.random();

        return new Color( red, green, blue );

    }


}
