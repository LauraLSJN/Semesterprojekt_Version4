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

    int speed; //Evt. getter og setter
    Farve farve;
    private Color colorBoks;
    //boolean randomPrice;

    //Konstruktør -> Sætter position
    //Indsæt currentSpeed, som parameter
    public FoodObjects(int speed, boolean randomPrice){ //herunder bliver værdierne som vi gerne vil have blive sat
        farve = new Farve();
        position = new Position(random.nextInt(size.getDisplayWidth()- size.getFoodObjectWidth()),0 ); //-gameObject size, så de ikke placeres udenfor display
        isRandomPrice(randomPrice);
        //price.setValuePrice(random.nextInt(price.getMinPrice(),price.getMaxPrice()));
       // price.setValuePrice(5);
        textInImage = String.valueOf(getPrice().getValuePrice()); //Henter valuePrice
        setColor();
        this.speed = speed;
       // this.speed = 1;
        //Level -> int currentSpeed -> Fra level liste
    }

    public void isRandomPrice( boolean randomPrice){
        if (randomPrice){
            price.setValuePrice(random.nextInt(price.getMinPrice(),price.getMaxPrice()));
        } else{
            price.setValuePrice(5);
        }
    }

    @Override
    public void update() {
        //Get metode til at hente y-koordinat
        int oldPos = position.getY();
        //Set metode til at sætte den ny y-koordinat
        position.setY(oldPos+speed);
        //System.out.println("SPEED: " + speed);

        //Print af FoodObjects position
        //System.out.println("Position FoodObjects");
        //System.out.println("Position X: " + position.getX() + "Position Y: " + position.getY());
    }



    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(size.getFoodObjectWidth(),size.getFoodObjectHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(colorBoks);
        graphics.fillRect(0, 0, size.getFoodObjectWidth(), size.getFoodObjectHeight());
        setText(graphics);
        graphics.dispose();
        return image;
    }

    //Price i firkanten
    //https://www.baeldung.com/java-add-text-to-image
    public void setText(Graphics2D graphics){
        attributedText = new AttributedString(textInImage);
        attributedText.addAttribute(TextAttribute.FONT, font); //Font
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE); //Sættes til foreground + farve = hvid
        graphics.drawString(attributedText.getIterator(), 2, (size.getFoodObjectHeight()/2)+5); //Placeres i billede -> X og y kordinat er i henhold til image

    }

    public void setColor(){
        if(getPrice().getValuePrice() >= 0){ //hvis prisen er lig eller mindre end 0
            this.colorBoks = farve.plusFarve; //grøn farve
        } else{
            this.colorBoks = farve.minusFarve; //rød farve
        }
    }

    //Printer arrayList til konsollen -> Anvendes til tjek
    @Override
    public String toString() {
        return "FoodObjects{" +
                "price=" + price +
                '}';
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}