import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

public class Display extends JFrame {
    MyFrame myFrame;
    private Canvas canvas;
    Image img = Toolkit.getDefaultToolkit().getImage("Ressourcer/shoppingMarket.jpg"); //Erstat stigen, men din egen sti
    //Anna sti: "/Users/annab/Desktop/shoppingMarket.jpg"
    //Chris sti:/Users/christinewulffeld/Desktop/shoppingMarket.jpg
    //Caro sti: "C:\Users\Carov\Desktop\shoppingMarket.jpg"
    //Laura sti: /Users/laura/Desktop/shoppingMarket.jpg


    public Display(int width, int height, Input input){
        setTitle("MyFoodSolver");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        add(canvas);
        addKeyListener(input);
        pack();

        canvas.createBufferStrategy(3);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void render(Game game){
        BufferStrategy bufferStartegy = canvas.getBufferStrategy();
        Graphics graphics = bufferStartegy.getDrawGraphics();





        graphics.drawImage(img, 0, 0,700,500,null); //"Tegner" baggrunden som billedet
        //graphics.fillRect(0, 0, canvas.getWidth(),canvas.getHeight()); //Kan anvendes hvis billede ikke virker



       // game.tekstBoks();
      /* if (game.isTest()){
           //game.getSprite();
           graphics.setColor(Color.black);
           graphics.fillRect(300,300,100,100);
           //System.out.println("TRUE");


       }else{
           //System.out.println("false ");
       }*/



        //Henter gameObjects (FoodObjcts & PlayerObjects) og tegner det
        //Anvender Lambda Expression

        game.tekstBoks(graphics);

        if(game.currentLevel == 0) {

            game.getGameObject().forEach(gameObject -> graphics.drawImage( //gameobject vi har foodobjekter og player i
                    gameObject.getSprite(),
                    gameObject.getPosition().getX(),
                    gameObject.getPosition().getY(),
                    null

            ));
            //Tegner shoppingBasket
            game.getShoppingBaskets().forEach(shoppingBasket -> graphics.drawImage(
                    shoppingBasket.getSprite(),
                    shoppingBasket.position.getX(),
                    shoppingBasket.position.getY(), null
            ));

            //Tegner tiden
            game.getTid().forEach(tid -> graphics.drawImage(
                    tid.getSprite(),
                    tid.position.getX(),
                    tid.position.getY(), null
            ));

            graphics.dispose();
            bufferStartegy.show();

        }


        if(game.currentLevel > 0){
            game.getTid().forEach(tid -> graphics.drawImage(
                    tid.getSprite(),
                    tid.position.getX(),
                    tid.position.getY(), null
            ));

            graphics.dispose();
            bufferStartegy.show();
        }





    }







}
