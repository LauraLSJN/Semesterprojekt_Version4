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
    int collectedFood;
    int nowCollectedFood;


    private int fontSize = 15;
    private int shoppingBasketWidth = 125;
    private int shoppingBasketHeight = 105; //I alt er tekst 95 pixel. For at få 5 pixel i top og bund sættes height til 105
    private int textX = 5;
    private int textY = 25;
    private int configuration = 5;
    Font font = new Font("Monospaced", Font.PLAIN, fontSize);

    public ShoppingBasket(int shoppingbasket) {
        position = new Position(0, 0);
        this.maxValue = shoppingbasket; //sætter den efterspurgte værdi 5
        maxValueString = String.valueOf(maxValue); //Taler int om til String
        this.collectedFood = 0; //Indsamlet point
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
        BufferedImage image = new BufferedImage(shoppingBasketWidth, shoppingBasketHeight, BufferedImage.TYPE_INT_RGB); //Laver Buffered billede
        Graphics2D graphics = image.createGraphics(); //Instans af Graphics2D klasse så vi kan bruge klassen
        graphics.setColor(Color.PINK); //Farve på billedet
        graphics.fillRect(0, 0, shoppingBasketWidth, shoppingBasketWidth); //fylder rect - Placeres ovenpå Image

        //Tegner tekst i billede
        setText(graphics, "Shoppingkurv:", textX, textY);
        setText(graphics, "Indsamlet:", textX, textY*2+fontSize+configuration);
        setText(graphics, maxValueString, textX + fontSize, textY + fontSize+configuration); //Tegner maxValue
        setText(graphics, String.valueOf(nowCollectedFood), textX + fontSize, textY*3+fontSize+configuration); //Tegner ShoppingBasket nowCollected værdi

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
