
import java.awt.Color;

//Klasse til at generer random farve
public class Farve {
    public Color randomColor;
    public Color negativeColor;
    public Color positiveColor;

    public ColorInGame() {
        this.randomColor = setRandomColor();
        this.negativeColor = Color.red;
        this.positiveColor = Color.green;
    }

    //Funktion der generer random farver
    public Color setRandomColor() {
        float red   = (float) Math.random();
        float green = (float) Math.random();
        float blue  = (float) Math.random();

        return new Color( red, green, blue );
    }
}
