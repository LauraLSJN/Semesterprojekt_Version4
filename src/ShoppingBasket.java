import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.Timer;
import java.util.TimerTask;


public class ShoppingBasket {
    Position position;
    int maxValue;
    String maxValueString;
    AttributedString attributedText;

    int rectx = 10;
    int recty = 10;
    int rectWidth = 100;
    int rectHeight = 100;
    int fontSize = 15;

    Font font = new Font("Monospaced", Font.BOLD, fontSize);
    int collectedFood;
    // int oldCollectedFood;
    int nowCollectedFood;

    public ShoppingBasket(int shoppingbasket) {
        position = new Position(0, 0);
        this.maxValue = shoppingbasket;
        maxValueString = String.valueOf(maxValue);
        this.collectedFood = 0;
        //    this.oldCollectedFood = 0;
    }

    public void setCollectedFood(int collectedFood) {
        this.collectedFood = collectedFood;
        System.out.println("SET METODE: " + collectedFood);
    }


    public void addCollectedFood(int foodValue) {
        //System.out.println("NowCOllectedFoodFør = " +this.nowCollectedFood); //Kontrol
        this.nowCollectedFood = this.nowCollectedFood + foodValue; //Opdaterer nowCollectedFood
        //System.out.println("NowCOllectedFoodEfter = " +this.nowCollectedFood); //Kontrol

    }

    public Image getSprite() {
        BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(rectx, recty, rectWidth, rectHeight);
        setText(graphics, maxValueString, rectx + fontSize, recty + fontSize);
        setText(graphics, String.valueOf(nowCollectedFood), rectx + fontSize, recty + fontSize + 15);
        graphics.dispose();
        return image;
    }

    //Price i firkanten
    //https://www.baeldung.com/java-add-text-to-image
    public void setText(Graphics2D graphics, String text, int x, int y) {
        attributedText = new AttributedString(text);
        attributedText.addAttribute(TextAttribute.FONT, font); //Font
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE); //Sættes til foreground + farve = hvid
        graphics.drawString(attributedText.getIterator(), x, y); //Placeres i billede -> X og y kordinat er i henhold til image
    }


}
