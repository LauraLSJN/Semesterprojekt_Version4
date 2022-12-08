import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    JButton button;
    JLabel label;
    public boolean isPressed = false;

    MyFrame(){
        label = new JLabel();

        button = new JButton();
        button.setBounds(200,200,200,50);
        button.addActionListener(this);
        button.setText("START GAME");
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        //button.getVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans",Font.BOLD,15));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.red);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,500);
        this. setVisible(true);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            System.out.println("virker");
            label.setVisible(true);
            isPressed = true;

        }
    }
}