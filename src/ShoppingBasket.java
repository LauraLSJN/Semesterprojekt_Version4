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
    int fontSize = 15;

    Font font = new Font("Monospaced", Font.PLAIN, fontSize);
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
        int shoppingBasketWidth = 125;
        int shoppingBasketHeight = 105; //Text 95 - height 105, betyder at der er 5 pixel i top og bund
        int textX = 5;
        int textY = 25;
        int configuration = 5;
        BufferedImage image = new BufferedImage(shoppingBasketWidth, shoppingBasketHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, shoppingBasketWidth, shoppingBasketWidth);
        setText(graphics, "Shoppingkurv:", textX, textY);
        setText(graphics, maxValueString, textX + fontSize, textY + fontSize+configuration);

        setText(graphics, "Indsamlet:", textX, textY*2+fontSize+configuration); //25+25+15+5
        setText(graphics, String.valueOf(nowCollectedFood), textX + fontSize, textY*3+fontSize+configuration); //25+25+25+15
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
