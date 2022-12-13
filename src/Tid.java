import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.text.DecimalFormat;


public class Tid {

    Position position;

    int minSecond;
    int second=15;
    int minute;


    String ddSecond;
    String ddMinute;
    String ddMinSec;
    DecimalFormat ddFormat = new DecimalFormat("00");
    int fontSize = 15;
    int xText = 15;
    int width = 100;
    int height = 50;



    //TEKST
    Font font = new Font("Monospaced", Font.BOLD, fontSize);
    String textInImage;
    AttributedString attributedText;
    Size size;




    public Tid() {
        size = new Size();
        position = new Position(size.getDisplayWidth() - width, 0);
        this.textInImage = "00:00:00";

       // this.minSecond = 0;
        //this.second = 0;
        //this.minute = 0;
    }

    public void update() {


        if (minSecond == 0 && minute == 0 && second == 0) {
            minSecond = 0;
            minute = 0;
            second = 0;

        } else {
            minSecond--;
            if (minSecond == -1) {
                minSecond = 59;
                second--;
            }
            if (second == -1) {
                second = 59;
                minute--;
            }
        }

        ddSecond = ddFormat.format(second);
        ddMinute = ddFormat.format(minute);
        ddMinSec = ddFormat.format(minSecond);

        this.textInImage = (ddMinute + ":" + ddSecond + ":" + ddMinSec);

    }


    public Image getSprite() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
        setText(graphics);
        graphics.dispose();
        return image;
    }

    public void setText(Graphics2D graphics) {
        attributedText = new AttributedString(textInImage);
        attributedText.addAttribute(TextAttribute.FONT, font); //Font
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE); //Sættes til foreground + farve = hvid
        graphics.drawString(attributedText.getIterator(), xText, (height / 2) + 5); //Placeres i billede -> X og y kordinat er i henhold til image
    }

    public void stopTid() {
        this.minute = 0;
        this.second = 0;
        this.minSecond = 0;
    }




    public void setMinSecond(int minSecond) {
        this.minSecond = minSecond;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMinSecond() {
        return minSecond;
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }


}
