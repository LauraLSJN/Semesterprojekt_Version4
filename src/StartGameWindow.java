//https://stackoverflow.com/questions/53699325/adding-start-stop-reset-button-to-simple-java-game
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class StartGameWindow {

    //Laver Start knap
    public void StartBoks() {
        JFrame window = new JFrame();//tegner vinduet
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(700, 500));
        window.setTitle("MyFoodSolver");//titel pa vinduet
        GamePanel gamePanel = new GamePanel(window);//tegner GamePanel noget som man kan bruge men det gor vi ikke, kan dog ikke slettes fordi saa virker actionlistener ikke
        window.add(gamePanel);//tilfojer det gamePanel vi ikke bruger

        //Knap
        JButton startBtn = new JButton("Klik her for at starte MyFoodSolver");//Det som skla staa i vores startknap
        startBtn.setFont(new Font("Monospaced", Font.BOLD, 25));//Fonten paa teksten
        startBtn.setForeground(Color.PINK);//Farve på tekst
        startBtn.setPreferredSize(new Dimension(700, 500));//Knappens størrelse -> Sættes til samme som windows dimension
        startBtn.addActionListener(e -> gamePanel.start());//Actionlistener naar knappen trykkes skal spillet starte --> void start

        window.add(startBtn, BorderLayout.PAGE_START);//tegner vores knap med alt det forrige indhold som str, farve og font
        window.pack();//tegner alt indhold
        window.setLocationRelativeTo(null);//Placerer vinduet
        window.setVisible(true);//gor vinduet synlig

    }

}
