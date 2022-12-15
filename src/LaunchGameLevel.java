import javax.swing.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
   //private JFrame window;
    private static Timer timer;

    public LaunchGameLevel(JFrame window) {
        ActionListener Game = e -> {
            window.dispose();
        };
        timer = new Timer(200, Game);
    }

   public void start() {
        timer.start();//Henter knappen som vi kan trykke paa saa vi bliver fort videre til naeste trin
        new Thread(new GameLoop(new Game(1))).start();//starter spillet

    }

    public void startLevel(int currentlevel){
        timer.start();
        new Thread(new GameLoop(new Game(currentlevel))).start();//starter spillet
    }
}
