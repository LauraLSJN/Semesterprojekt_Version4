import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerObject extends GameObject {
    private Controller controller;
    private int speedX;
    private int speed;
    private int oldPosX;
    Image imgShoppingkurv = Toolkit.getDefaultToolkit().getImage("Ressourcer/shoppingKurv3.png");

    public PlayerObject(Controller controller, int speed) { //, int speed
        super();
        this.controller = controller;
        position = new Position(350,440);
        this.speed = speed;
    }

    @Override
    public void update() {
         speedX = 0;
        oldPosX = position.getX(); //Gemmer tidligere x koordinat
            if (controller.isRequestiongLeft()) {
                speedX = speedX - this.speed;
            }
            if (controller.isRequestiongRight()) {
                speedX = speedX + this.speed;
            }
        position.setX(oldPosX+ speedX); //Sætter ny x-koordinat
    }


    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(size.getPlayerObjectWidth(), size.getPlayerObjectHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(imgShoppingkurv,0,0,size.getPlayerObjectWidth(), size.getPlayerObjectHeight(),null);
        return image;
    }


}
