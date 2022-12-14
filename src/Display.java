import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

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

        /*Button b = new Button("Klik her");
        b.setBounds(300,300,100,100);*/


        pack();
        canvas.createBufferStrategy(3);
        setLocationRelativeTo(null);
        setVisible(true);
    }


/*
    public void levelBoks(int level){
       // JFrame canvas = new JFrame();//tegner vinduet
      //  canvas.setTitle("MyFoodSolver");//titel pa vinduet
       // StartGameWindow.GamePanel gamePanel = new StartGameWindow.GamePanel(window);//tegner GamePanel noget som man kan bruge men det gor vi ikke, kan dog ikke slettes fordi saa virker actionlistener ikke
      //  window.add(gamePanel);//tilfojer det gamePanel vi ikke bruger
       // StartGameWindow.GamePanel gamePanel = new StartGameWindow.GamePanel(canvas);

        StartGameWindow.GamePanel = new StartGameWindow.GamePanel();

        JButton startBtn = new JButton("Klik her for at starte level " + level + "!");//Det som skla staa i vores startknap
        startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 32));//Fonten paa teksten
        // startBtn.setForeground(Color.GREEN);//Farve paa tekst
        //startBtn.setPreferredSize(700, 80);//Storrelsen paa knappen
        // startBtn.setBackground(Color.green);//Farve paa knappen
        startBtn.addActionListener(e -> gamePanel.level1(level));//Actionlistener naar knappen trykkes skal spillet starte --> void start
        //canvas.add(startBtn, BorderLayout.PAGE_START);//tegner vores knap med alt det forrige indhold som str, farve og font

        //canvas.setPreferredSize(new Dimension(700, 500));//vinduet str udenom knappen
        //canvas.pack();//tegner alt indhold
       // canvas.setLocationRelativeTo(null);//Placerer vinduet
        //canvas.setVisible(true);//gor vinduet synlig
    }*/

    public void levelBoks(int level){
        // JFrame window = new JFrame();//tegner vinduet
        JFrame window = new JFrame();
        window.setTitle("MyFoodSolver");//titel pa vinduet
        window.setPreferredSize(new Dimension(700, 500));//vinduet str udenom knappen
        GamePanel gamePanel = new GamePanel(window);//tegner GamePanel noget som man kan bruge men det gor vi ikke, kan dog ikke slettes fordi saa virker actionlistener ikke
        window.add(gamePanel);//tilfojer det gamePanel vi ikke bruger

       // gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));
        JLabel jlabel = new JLabel("<html><div style='text-align: center;'> Tillykke <BR> Du har vundet <BR> </div></html>", SwingConstants.CENTER ); //, SwingConstants.CENTER
       // JLabel jlabel = new JLabel("<html> Tillykke <BR> Du har vundet </html> ");
        //jlabel.setHorizontalAlignment(SwingConstants.CENTER);
        //jlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //jlabel.setVerticalAlignment(SwingConstants.CENTER);
        jlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        gamePanel.add(jlabel);
        //window.add(jlabel);
        jlabel.setLayout((new BoxLayout(jlabel, BoxLayout.PAGE_AXIS)));

        /*
        JLabel label = new JLabel("<html> <div style='text-align: center;'>  Hej </div></html");
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
       // label.setHorizontalAlignment(SwingConstants.CENTER);
        gamePanel.setAlignmentX(Component.TOP_ALIGNMENT);
        gamePanel.add(label);
*/

        //game.tekstBoks(graphics);
        JButton startBtn = new JButton("Klik her for at starte level " + level + "!" );//Det som skla staa i vores startknap
       // gamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        //startBtn.setLayout(new BoxLayout(startBtn, BoxLayout.LINE_AXIS));
       // startBtn.setLocation(400,400);
       // startBtn.setSize(50,50);
        //startBtn.setHorizontalTextPosition(SwingConstants.CENTER);
       // startBtn.setBounds(500,500,100,100);
        startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 32));//Fonten paa teksten
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
       // jlabel.setHorizontalAlignment(SwingConstants.CENTER);
        // startBtn.setForeground(Color.GREEN);//Farve paa tekst
        //startBtn.setBounds(300,300,100,100);

        //startBtn.setPreferredSize(new Dimension(100, 100));//Storrelsen paa knappen
        // startBtn.setBackground(Color.green);//Farve paa knappen

        startBtn.addActionListener(e -> gamePanel.level1(level));//Actionlistener naar knappen trykkes skal spillet starte --> void start
        startBtn.setLayout((new BoxLayout(startBtn, BoxLayout.PAGE_AXIS)));
        gamePanel.add(startBtn,BorderLayout.CENTER);


        //startBtn.setBounds(300,300,100,100);
       // gamePanel.add( startBtn, BorderLayout.PAGE_START);//tegner vores knap med alt det forrige indhold som str, farve og font


        window.pack();//tegner alt indhold
        window.setLocationRelativeTo(null);//Placerer vinduet
        window.setVisible(true);//gor vinduet synlig
    }

    public void render(Game game){
        BufferStrategy bufferStartegy = canvas.getBufferStrategy();
        Graphics graphics = bufferStartegy.getDrawGraphics();
        graphics.drawImage(img, 0, 0,700,500,null); //"Tegner" baggrunden som billedet
        //graphics.fillRect(0, 0, canvas.getWidth(),canvas.getHeight()); //Kan anvendes hvis billede ikke virker

        paint(graphics);



        //Henter gameObjects (FoodObjcts & PlayerObjects) og tegner det
        //Anvender Lambda Expression
       // levelBoks(game.currentLevel);

        drawCurrentLevel(graphics,game.toString());
        game.tekstBoks(graphics);

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


if (game.isWon() == true){
    graphics.dispose();
}



    }

    public void drawCurrentLevel(Graphics graphics, String StringCurrentLevel){
        //graphics.setFont(font);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(500,500,100,100);
        graphics.setColor(Color.GREEN);
        graphics.drawString(StringCurrentLevel,150,150);

    }


    @Override
    public void paint(Graphics g){
        JButton button = new JButton();
        button.isDefaultButton();
        button.setBounds(300,300,100,100);
        button.setForeground(Color.black);
        button.setText("KNAP");
    }







}
