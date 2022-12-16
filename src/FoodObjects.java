import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.Random;

public class FoodObjects extends GameObject {
    //globale variabler som vi bruger
    private Random random = new Random();
    private Font font = new Font("Monospaced", Font.BOLD, 15);
    private String textInFoodObject;
    private AttributedString attributedText;
    private int speedFoodObject;
    private Color colorFoodObject;
    private ColorInGame color;

    public FoodObjects(int speed, boolean randomPrice, boolean randomColor){
        position = new Position(random.nextInt(size.getDisplayWidth()- size.getFoodObjectWidth()),0 ); //Placeres indenfor display -> display width-foodObjectWidth, så hele firkenten er indenfor display
        isRandomPrice(randomPrice);
        color = new ColorInGame();
        textInFoodObject = String.valueOf(price.getValuePrice());
        setColor(randomColor);
        this.speedFoodObject = speed;
    }

    @Override
    public void updateGameObject() {
        int oldPosition = position.getY(); //Position.getY metode til at hente y-koordinat
        position.setY(oldPosition + speedFoodObject); //position.setY metode til at sætte den nye y-koordinat
    }

    //Metode til at tjekke hvorvidt variablen price skal være random eller ej
    public void isRandomPrice(boolean randomPrice){
        if (randomPrice){
            price.setValuePrice(random.nextInt(price.getMinPrice(),price.getMaxPrice()+1)); //fra -10 til 10
        } else{
            price.setValuePrice(5); //Sætter prisen til 5 altid
        }
    }

    //Metode til at sætte farven i foodObjects
    public void setColor(boolean randomColor){
        if (randomColor){
            this.colorFoodObject = color.randomColor; //Random farve
        }else {
        if(getPrice().getValuePrice() >= 0){ //hvis prisen er lig eller større end 0
            this.colorFoodObject = color.positiveColor; //grøn farve
        } else{
            this.colorFoodObject = color.negativeColor; //rød farve
        }
        }
    }

    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(size.getFoodObjectWidth(),size.getFoodObjectHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(colorFoodObject); //Sætter farven
        graphics.fillRect(0, 0, size.getFoodObjectWidth(), size.getFoodObjectHeight());
        setText(graphics);
        return image;
    }

    //Tekst i foodObjects (Grafisk)
    public void setText(Graphics2D graphics){
        attributedText = new AttributedString(textInFoodObject);
        attributedText.addAttribute(TextAttribute.FONT, font); //Font
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE); //Sættes til foreground + farve = hvid
        graphics.drawString(attributedText.getIterator(), 2, (size.getFoodObjectHeight()/2)+5); //Placeres i billede -> X og y koordinat er i henhold til image

    }

    //Printer arrayList til konsollen -> Anvendes til tjek
    @Override
    public String toString() {
        return "FoodObjects{" +
                "price=" + price +
                '}';
    }
}