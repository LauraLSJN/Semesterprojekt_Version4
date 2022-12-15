import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas;
    public Image img = Toolkit.getDefaultToolkit().getImage("Ressourcer/shoppingMarket.jpg"); //Billede er i Ressourcer Mappen
    private int widthDisplay;
    private int heightDisplay;
    private String titleDisplay = "MyFoodSolver";

    public Display(int width, int height, Input input){
        this.widthDisplay = width;
        this.heightDisplay = height;
        setTitle(titleDisplay);//titel pa user interface
        setResizable(false);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(widthDisplay, heightDisplay)); //Størrelse på user interface
        add(canvas);
        addKeyListener(input);
        pack();
        canvas.createBufferStrategy(3); //Mekanisme til at organisere kompleks memory af grafikken
        setLocationRelativeTo(null); //Canvas åbner i midten af skærmen
        setVisible(true);
    }

    public void renderDisplay(Game game){
       BufferStrategy bufferStartegy = canvas.getBufferStrategy();
       Graphics graphics = bufferStartegy.getDrawGraphics();
       graphics.drawImage(img, 0, 0, widthDisplay, heightDisplay,null); //Tilføjer billedet -> Baggrunden

        //Henter gameObjects (FoodObjects & PlayerObjects) og tegner det - Anvender Lambda Expression
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

            game.LevelTekstBoks(graphics); //Tegner level boksen
            graphics.dispose();
            bufferStartegy.show();
    }

    //Knapperne hvori man kan gå til næste eller samme level
    public void levelWindow(int level, boolean won){
        JFrame window = new JFrame(); //Nyt window
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setTitle(titleDisplay);//titel pa window
        window.setPreferredSize(new Dimension(widthDisplay, heightDisplay));//window størrelse
        GamePanel gamePanel = new GamePanel(window);
        window.add(gamePanel); //Tilføjer gamePanel til window
        Font font = new Font("Monospaced", Font.BOLD, 25); //Font og størrelse på tekst

        if (won) {
            if(level <6) {
                JLabel labelWonLevel = new JLabel("<html><div style='text-align: center;'> Tillykke <BR> Du har vundet <BR> </div></html>", SwingConstants.CENTER); //, SwingConstants.CENTER
                //Label
                labelWonLevel.setFont(font);
                gamePanel.add(labelWonLevel);
                labelWonLevel.setLayout((new BoxLayout(labelWonLevel, BoxLayout.PAGE_AXIS)));

                //Knap
                JButton buttonWonLevel = new JButton("Klik her for at starte level " + level + "!");//Det som skla staa i vores startknap
                buttonWonLevel.setFont(font);//Fonten paa teksten
                buttonWonLevel.setAlignmentX(Component.CENTER_ALIGNMENT);

                buttonWonLevel.addActionListener(e -> gamePanel.startLevel(level));//Actionlistener naar knappen trykkes skal spillet starte på det level som fåes med i parantesen
                buttonWonLevel.setLayout((new BoxLayout(buttonWonLevel, BoxLayout.PAGE_AXIS)));
                gamePanel.add(buttonWonLevel, BorderLayout.CENTER); //tilføjer knappen til gamePanel

            }else if (level == 6){ //level bliver opdateret i game inden den sendes med videre her, Derfor er man på level 6 når man har vundet.
               //Label
                JLabel labelWonGame = new JLabel("<html><div style='text-align: center;'> Tillykke <BR> Du har vundet HELE SPILLET <BR> </div></html>", SwingConstants.CENTER); //, SwingConstants.CENTER
                labelWonGame.setFont(font);
                gamePanel.add(labelWonGame);
                labelWonGame.setLayout((new BoxLayout(labelWonGame, BoxLayout.PAGE_AXIS)));

                //Knap
                JButton buttonWonGame = new JButton("Klik her for at starte spillet forfra!");//Tekst i knap
                buttonWonGame.setFont(font);//Fonten paa teksten
                buttonWonGame.setAlignmentX(Component.CENTER_ALIGNMENT);

                buttonWonGame.addActionListener(e -> gamePanel.startLevel(1));//Actionlistener naar knappen trykkes skal spillet starte på level 1
                buttonWonGame.setLayout((new BoxLayout(buttonWonGame, BoxLayout.PAGE_AXIS)));
                gamePanel.add(buttonWonGame, BorderLayout.CENTER); //tilføjer knappen til gamePanel

            }

        } else if (won == false){
            //Label
            JLabel labelLost = new JLabel("<html><div style='text-align: center;'> ØV BØV <BR> Du har tabt <BR> </div></html>", SwingConstants.CENTER); //, SwingConstants.CENTER
            labelLost.setFont(font);
            gamePanel.add(labelLost);
            labelLost.setLayout((new BoxLayout(labelLost, BoxLayout.PAGE_AXIS)));

            //Knap
            JButton buttonLost = new JButton("Klik her for at starte level " + level + " IGEN!");//Tekst i knappen
            buttonLost.setFont(font);
            buttonLost.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonLost.addActionListener(e -> gamePanel.startLevel(level));//Actionlistener naar knappen trykkes skal metoden startLevel fra gamePanel kaldes med level i argument
            buttonLost.setLayout((new BoxLayout(buttonLost, BoxLayout.PAGE_AXIS)));
            gamePanel.add(buttonLost, BorderLayout.CENTER);
        }

        window.pack();//tegner alt indhold
        window.setLocationRelativeTo(null);//Placerer window på skærmen
        window.setVisible(true);//Gør vinduet synlig
    }
}
