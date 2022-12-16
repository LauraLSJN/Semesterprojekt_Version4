import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.text.DecimalFormat;

public class GameTime {
    Position position;
    int minSecond;
    int second;
    int minute = 2;
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

    public GameTime() {
        size = new Size();
        position = new Position(size.getDisplayWidth() - width, 0);
    }

    public void updateGameTime() {
        if (milliSecond == 0 && minute == 0 && second == 0) {
            milliSecond = 0;
            minute = 0;
            second = 0;

        } else {
            milliSecond--;
            if (milliSecond == -1) {
                milliSecond = 59;
                second--;
            }
            if (second == -1) {
                second = 59;
                minute--;
            }
        }
        dfSecond = format.format(second);
        dfMinute = format.format(minute);
        dfMinSec = format.format(milliSecond);
        this.textInImage = (dfMinute + ":" + dfSecond + ":" + dfMinSec);

    }

    public void stopTime() {
        this.minute = 0;
        this.second = 0;
        this.milliSecond = 0;
    }

    public Image getSprite() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, width, height);
        setText(graphics);
        return image;
    }

    public void setText(Graphics2D graphics) {
        attributedText = new AttributedString(textInImage);
        attributedText.addAttribute(TextAttribute.FONT, font); //Font
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE); //Sættes til foreground + farve = hvid
        graphics.drawString(attributedText.getIterator(), xText, (height / 2) + 5); //Placeres i billede -> X og y kordinat er i henhold til image
    }


    public int getMilliSecond() {
        return milliSecond;
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }

}
