import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {
    private Canvas canvas;
    Image img = Toolkit.getDefaultToolkit().getImage("Ressourcer/shoppingMarket.jpg"); //Billede er i Ressourcer Mappen

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

    public void render(Game game){
       BufferStrategy bufferStartegy = canvas.getBufferStrategy();
        Graphics graphics = bufferStartegy.getDrawGraphics();
        graphics.drawImage(img, 0, 0,700,500,null); //"Tegner" baggrunden som billedet
        //graphics.fillRect(0, 0, canvas.getWidth(),canvas.getHeight()); //Kan anvendes hvis billede ikke virker

        //Henter gameObjects (FoodObjcts & PlayerObjects) og tegner det - Anvender Lambda Expression
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

            game.LevelTekstBoks(graphics);
            graphics.dispose();
            bufferStartegy.show();
    }

    //Knapperne hvori man kan gå til næste eller samme level
    public void levelBoks(int level, boolean won ){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setTitle("MyFoodSolver");//titel pa vinduet
        window.setPreferredSize(new Dimension(700, 500));//window størrelse
        GamePanel gamePanel = new GamePanel(window);
        window.add(gamePanel);//Tilføjer det gamePanel vi ikke bruger

        if (won) {
            if(level <6) {
                JLabel jlabel = new JLabel("<html><div style='text-align: center;'> Tillykke <BR> Du har vundet <BR> </div></html>", SwingConstants.CENTER); //, SwingConstants.CENTER

                //jlabel.setHorizontalAlignment(SwingConstants.CENTER);
                //jlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                jlabel.setFont(new Font("Monospaced", Font.BOLD, 32));
                gamePanel.add(jlabel);
                jlabel.setLayout((new BoxLayout(jlabel, BoxLayout.PAGE_AXIS)));

                JButton startBtn = new JButton("Klik her for at starte level " + level + "!");//Det som skla staa i vores startknap
                startBtn.setFont(new Font("Monospaced", Font.BOLD, 32));//Fonten paa teksten
                startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

                startBtn.addActionListener(e -> gamePanel.startLevel(level));//Actionlistener naar knappen trykkes skal spillet starte --> void start
                startBtn.setLayout((new BoxLayout(startBtn, BoxLayout.PAGE_AXIS)));
                gamePanel.add(startBtn, BorderLayout.CENTER); //tegner vores knap med alt det forrige indhold som str, farve og font

            }else if (level == 6){

                JLabel jlabel = new JLabel("<html><div style='text-align: center;'> Tillykke <BR> Du har vundet HELE SPILLET <BR> </div></html>", SwingConstants.CENTER); //, SwingConstants.CENTER

                //jlabel.setHorizontalAlignment(SwingConstants.CENTER);
                //jlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                jlabel.setFont(new Font("Monospaced", Font.BOLD, 32));
                gamePanel.add(jlabel);
                jlabel.setLayout((new BoxLayout(jlabel, BoxLayout.PAGE_AXIS)));

                JButton startBtn = new JButton("Klik her for at starte spillet forfra!");//Tekst i knap
                startBtn.setFont(new Font("Monospaced", Font.BOLD, 32));//Fonten paa teksten
                startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

                startBtn.addActionListener(e -> gamePanel.startLevel(1));//Actionlistener naar knappen trykkes skal spillet starte --> void start
                startBtn.setLayout((new BoxLayout(startBtn, BoxLayout.PAGE_AXIS)));
                gamePanel.add(startBtn, BorderLayout.CENTER); //tegner vores knap med alt det forrige indhold som str, farve og font

            }

        } else if (won == false ){
            JLabel jlabel = new JLabel("<html><div style='text-align: center;'> ØV BØV <BR> Du har tabt <BR> </div></html>", SwingConstants.CENTER); //, SwingConstants.CENTER
            jlabel.setFont(new Font("Monospaced", Font.BOLD, 32));
            gamePanel.add(jlabel);
            jlabel.setLayout((new BoxLayout(jlabel, BoxLayout.PAGE_AXIS)));

            JButton startBtn = new JButton("Klik her for at starte level " + level + " IGEN!");//Det som skla staa i vores startknap
            startBtn.setFont(new Font("Monospaced", Font.BOLD, 32));//Fonten paa teksten
            startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            startBtn.addActionListener(e -> gamePanel.startLevel(level));//Actionlistener naar knappen trykkes skal spillet starte --> void start
            startBtn.setLayout((new BoxLayout(startBtn, BoxLayout.PAGE_AXIS)));
            gamePanel.add(startBtn, BorderLayout.CENTER);
        }

        window.pack();//tegner alt indhold
        window.setLocationRelativeTo(null);//Placerer vinduet
        window.setVisible(true);//gor vinduet synlig
    }
}
