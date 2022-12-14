import javax.swing.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    JFrame window;
    private static Timer timer;

    public GamePanel(JFrame window) {
        this.window = window;
        ActionListener Game = e -> {
            window.dispose();
        };
        timer = new Timer(200, Game);//VED IKKE HELT HVAD DER SKER HER FOR GAME ER BARE SAT IND UDEN TANKE MEN UDEN VIRKER KDOEN IKKE
    }

    void start() {
        timer.start();//Henter knappen som vi kan trykke paa saa vi bliver fort videre til naeste trin
        new Thread(new GameLoop(new Game(5))).start();//starter spillet

    }

    void startLevel(int currentlevel){
        timer.start();
        new Thread(new GameLoop(new Game(currentlevel))).start();//starter spillet
    }
}
