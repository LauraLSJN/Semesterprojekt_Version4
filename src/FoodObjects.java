import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.Random;

public class FoodObjects extends GameObject { //globale variabler som vi bruger
    Random random = new Random();
    Font font = new Font("Monospaced", Font.BOLD, 15);
    String textInImage;
    AttributedString attributedText;
    int speed;
    Farve farve;
    private Color colorBoks;

    public FoodObjects(int speed, boolean randomPrice, boolean randomColor){
        farve = new Farve();
        position = new Position(random.nextInt(size.getDisplayWidth()- size.getFoodObjectWidth()),0 ); //-gameObject size, så de ikke placeres udenfor display
        isRandomPrice(randomPrice);
        textInImage = String.valueOf(getPrice().getValuePrice()); //Henter valuePrice
        setColor(randomColor);
        this.speed = speed;
    }

    @Override
    public void update() {
        int oldPos = position.getY(); //Position.getY metode til at hente y-koordinat
        position.setY(oldPos+speed); //position.setY metode til at sætte den ny y-koordinat
    }

    //Funktion til at tjekke hvorvidt variablen price skal være random eller ej
    public void isRandomPrice( boolean randomPrice){
        if (randomPrice){
            price.setValuePrice(random.nextInt(price.getMinPrice(),price.getMaxPrice()));
        } else{
            price.setValuePrice(5);
        }
    }
    //Funktion at sætte farven i foodObjects
    public void setColor(boolean randomColor){
        if (randomColor){
            this.colorBoks = farve.randomColor;
        }else {
        if(getPrice().getValuePrice() >= 0){ //hvis prisen er lig eller mindre end 0
            this.colorBoks = farve.plusFarve; //grøn farve
        } else{
            this.colorBoks = farve.minusFarve; //rød farve
        }
        }
    }

    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(size.getFoodObjectWidth(),size.getFoodObjectHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(colorBoks);
        graphics.fillRect(0, 0, size.getFoodObjectWidth(), size.getFoodObjectHeight());
        setText(graphics);
        return image;
    }

    //Tekst i foodObjects (Grafisk)
    //https://www.baeldung.com/java-add-text-to-image
    public void setText(Graphics2D graphics){
        attributedText = new AttributedString(textInImage);
        attributedText.addAttribute(TextAttribute.FONT, font); //Font
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE); //Sættes til foreground + farve = hvid
        graphics.drawString(attributedText.getIterator(), 2, (size.getFoodObjectHeight()/2)+5); //Placeres i billede -> X og y kordinat er i henhold til image

    }

    //Printer arrayList til konsollen -> Anvendes til tjek
    @Override
    public String toString() {
        return "FoodObjects{" +
                "price=" + price +
                '}';
    }


}