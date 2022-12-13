//https://stackoverflow.com/questions/53699325/adding-start-stop-reset-button-to-simple-java-game
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StartGameWindow {

    private static Timer timer;
    static Size size;
    private static Input input; //Input fra brugeren
    private static Display display;


    //Laver Start knap
    public void StartBoks() {
        JFrame window = new JFrame();//tegner vinduet
        window.setTitle("MyFoodSolver");//titel pa vinduet
        GamePanel gamePanel = new GamePanel(window);//tegner GamePanel noget som man kan bruge men det gor vi ikke, kan dog ikke slettes fordi saa virker actionlistener ikke
        window.add(gamePanel);//tilfojer det gamePanel vi ikke bruger

        JButton startBtn = new JButton("Klik her for at starte My Food Solver!!");//Det som skla staa i vores startknap
        startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 32));//Fonten paa teksten
        startBtn.setForeground(Color.GREEN);//Farve paa tekst
        startBtn.setPreferredSize(new Dimension(700, 500));//Storrelsen paa knappen
        // startBtn.setBackground(Color.green);//Farve paa knappen
        startBtn.addActionListener(e -> gamePanel.start());//Actionlistener naar knappen trykkes skal spillet starte --> void start
        window.add(startBtn, BorderLayout.PAGE_START);//tegner vores knap med alt det forrige indhold som str, farve og font

        window.setPreferredSize(new Dimension(700, 500));//vinduet str udenom knappen
        window.pack();//tegner alt indhold
        window.setLocationRelativeTo(null);//Placerer vinduet
        window.setVisible(true);//gor vinduet synlig

    }


    //Denne classe er n'dvendig for at tegne classen
    static class GamePanel extends JPanel {

        GamePanel(JFrame window ) {

            ActionListener Game = e -> {
                window.dispose();
            };
            timer = new Timer(200, Game);//VED IKKE HELT HVAD DER SKER HER FOR GAME ER BARE SAT IND UDEN TANKE MEN UDEN VIRKER KDOEN IKKE
        }
        //Her sker selve handlingen fra startknap til spillets start
        void start() {
            timer.start();//Henter knappen som vi kan trykke paa saa vi bliver fort videre til naeste trin
           // Game game = new Game();//Henter game
            new Thread(new GameLoop(new Game())).start();//starter spillet

        }
    }
}
