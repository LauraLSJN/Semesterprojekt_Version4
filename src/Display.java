import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas;

    Image img = Toolkit.getDefaultToolkit().getImage("Ressourcer/shoppingMarket.jpg"); //Erstat stigen, men din egen sti
    //Anna sti: "/Users/annab/Desktop/shoppingMarket.jpg"
    //Chris sti:/Users/christinewulffeld/Desktop/shoppingMarket.jpg
    //Caro sti: "C:\Users\Carov\Desktop\shoppingMarket.jpg"
    //Laura sti: /Users/laura/Desktop/shoppingMarket.jpg


    public Display(int width, int height, Input input){

        setTitle("MyFoodSolver");//titel pa vinduet
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

    public void levelBoks(int level, boolean won ){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);

      if (won) {

          if(level <5) {


              window.setTitle("MyFoodSolver");//titel pa vinduet
              window.setPreferredSize(new Dimension(700, 500));//vinduet str udenom knappen
              GamePanel gamePanel = new GamePanel(window);//tegner GamePanel noget som man kan bruge men det gor vi ikke, kan dog ikke slettes fordi saa virker actionlistener ikke
              window.add(gamePanel);//tilfojer det gamePanel vi ikke bruger

              JLabel jlabel = new JLabel("<html><div style='text-align: center;'> Tillykke <BR> Du har vundet <BR> </div></html>", SwingConstants.CENTER); //, SwingConstants.CENTER

              //jlabel.setHorizontalAlignment(SwingConstants.CENTER);
              //jlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
              jlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
              gamePanel.add(jlabel);
              jlabel.setLayout((new BoxLayout(jlabel, BoxLayout.PAGE_AXIS)));

              JButton startBtn = new JButton("Klik her for at starte level " + level + "!");//Det som skla staa i vores startknap
              startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 32));//Fonten paa teksten
              startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

              startBtn.addActionListener(e -> gamePanel.level1(level));//Actionlistener naar knappen trykkes skal spillet starte --> void start
              startBtn.setLayout((new BoxLayout(startBtn, BoxLayout.PAGE_AXIS)));
              gamePanel.add(startBtn, BorderLayout.CENTER); //tegner vores knap med alt det forrige indhold som str, farve og font

              window.pack();//tegner alt indhold
              window.setLocationRelativeTo(null);//Placerer vinduet
              window.setVisible(true);//gor vinduet synlig
          }else if (level == 5){

              window.setTitle("MyFoodSolver");//titel pa vinduet
              window.setPreferredSize(new Dimension(700, 500));//vinduet str udenom knappen
              GamePanel gamePanel = new GamePanel(window);//tegner GamePanel noget som man kan bruge men det gor vi ikke, kan dog ikke slettes fordi saa virker actionlistener ikke
              window.add(gamePanel);//tilfojer det gamePanel vi ikke bruger

              JLabel jlabel = new JLabel("<html><div style='text-align: center;'> Tillykke <BR> Du har vundet HELE SPILLET <BR> </div></html>", SwingConstants.CENTER); //, SwingConstants.CENTER

              //jlabel.setHorizontalAlignment(SwingConstants.CENTER);
              //jlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
              jlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
              gamePanel.add(jlabel);
              jlabel.setLayout((new BoxLayout(jlabel, BoxLayout.PAGE_AXIS)));

              JButton startBtn = new JButton("Klik her for at starte spillet forfra!");//Det som skla staa i vores startknap
              startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 32));//Fonten paa teksten
              startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

              startBtn.addActionListener(e -> gamePanel.level1(1));//Actionlistener naar knappen trykkes skal spillet starte --> void start
              startBtn.setLayout((new BoxLayout(startBtn, BoxLayout.PAGE_AXIS)));
              gamePanel.add(startBtn, BorderLayout.CENTER); //tegner vores knap med alt det forrige indhold som str, farve og font

              window.pack();//tegner alt indhold
              window.setLocationRelativeTo(null);//Placerer vinduet
              window.setVisible(true);//gor vinduet synlig
          }
      } else if (won == false ){
          System.out.println("won == false ");

          window.setTitle("MyFoodSolver");//titel pa vinduet
          window.setPreferredSize(new Dimension(700, 500));//vinduet str udenom knappen
          GamePanel gamePanel = new GamePanel(window);//tegner GamePanel noget som man kan bruge men det gor vi ikke, kan dog ikke slettes fordi saa virker actionlistener ikke
          window.add(gamePanel);//tilfojer det gamePanel vi ikke bruger

          JLabel jlabel = new JLabel("<html><div style='text-align: center;'> ØV BØV <BR> Du har tabt <BR> </div></html>", SwingConstants.CENTER); //, SwingConstants.CENTER
          jlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
          gamePanel.add(jlabel);
          jlabel.setLayout((new BoxLayout(jlabel, BoxLayout.PAGE_AXIS)));


          JButton startBtn = new JButton("Klik her for at starte level " + level + " IGEN!");//Det som skla staa i vores startknap
          startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 32));//Fonten paa teksten
          startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
          startBtn.addActionListener(e -> gamePanel.level1(level));//Actionlistener naar knappen trykkes skal spillet starte --> void start
          startBtn.setLayout((new BoxLayout(startBtn, BoxLayout.PAGE_AXIS)));
          gamePanel.add(startBtn, BorderLayout.CENTER);

          window.pack();//tegner alt indhold
          window.setLocationRelativeTo(null);//Placerer vinduet
          window.setVisible(true);//gor vinduet synlig

      }
    }

    public void render(Game game){


       BufferStrategy bufferStartegy = canvas.getBufferStrategy();
        Graphics graphics = bufferStartegy.getDrawGraphics();
        graphics.drawImage(img, 0, 0,700,500,null); //"Tegner" baggrunden som billedet
        //graphics.fillRect(0, 0, canvas.getWidth(),canvas.getHeight()); //Kan anvendes hvis billede ikke virker



        //Henter gameObjects (FoodObjcts & PlayerObjects) og tegner det
        //Anvender Lambda Expression
       // levelBoks(game.currentLevel);

        drawCurrentLevel(graphics,game.toString());

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
            //game.tekstBoks(graphics);


      /*  if(game.currentLevel > 0){
            game.getTid().forEach(tid -> graphics.drawImage(
                    tid.getSprite(),
                    tid.position.getX(),
                    tid.position.getY(), null
            ));

            //game.tekstBoks(graphics);

            graphics.dispose();
            bufferStartegy.show();
        }*/

        /*
      /*  if(game.isWon() == true){
            setVisible(false);
        }*/





    }

    public void drawCurrentLevel(Graphics graphics, String StringCurrentLevel){
        //graphics.setFont(font);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(500,500,100,100);
        graphics.setColor(Color.GREEN);
        graphics.drawString(StringCurrentLevel,150,150);

    }







}
