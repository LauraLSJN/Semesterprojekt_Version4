import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerObject extends GameObject {
    private Controller controller;



    private int speedX;
    private int speed;
    private int oldPosX;
    //Image imgShoppingkurv = Toolkit.getDefaultToolkit().getImage("/Users/laura/Desktop/shoppingKurv3.png");
    Image imgShoppingkurv = Toolkit.getDefaultToolkit().getImage("Ressourcer/shoppingKurv3.png");



    public PlayerObject(Controller controller, int speed) { //, int speed
        super();
        this.controller = controller;
        //position = new Position(350,465); //Starter på denne position
        position = new Position(350,440);
        this.speed = speed;

    }


    @Override
    public void update() {
       speedX = speed;
        // speedX = 0;
        oldPosX = position.getX(); //Gemmer tidligere x koordinat

            if (controller.isRequestiongLeft()) {
                speedX--;
        }
            if (controller.isRequestiongRight()) {
                speedX++;
        }
        position.setX(oldPosX+ speedX); //Sætter ny x-koordinat
    }


    @Override
    public Image getSprite() {
        //BufferedImage image = new BufferedImage(size.getGameObjectWidth(), size.getGameObjectHeight(), BufferedImage.TYPE_INT_RGB);
        //BufferedImage image = new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB);
        BufferedImage image = new BufferedImage(size.getPlayerObjectWidth(), size.getPlayerObjectHeight(), BufferedImage.TYPE_INT_ARGB);


        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(imgShoppingkurv,0,0,size.getPlayerObjectWidth(), size.getPlayerObjectHeight(),null);

        //graphics.drawImage(imgShoppingkurv,0,0,size.getGameObjectWidth(),size.getGameObjectHeight(),null);
        //graphics.setColor(Color.darkGray);
       //graphics.fillRect(0, 0, size.getGameObjectWidth(), size.getGameObjectHeight());
        graphics.dispose();
        return image;
    }


}
