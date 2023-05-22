import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    final private Font mainFont = new Font("",Font.BOLD,18);
    JTextField tfPlayer1,tfPlayer2;
    

    public void initialize(){
        // FORM PANEL
        JLabel lbPlayer1 = new JLabel("PLAYER1:");
        lbPlayer1.setFont(mainFont);

        tfPlayer1 = new JTextField();
        tfPlayer1.setFont(mainFont);

        JLabel lbPlayer2 = new JLabel("PLAYER1:");
        lbPlayer2.setFont(mainFont);

        tfPlayer2 = new JTextField();
        tfPlayer2.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4,1,5,5));
        formPanel.add(lbPlayer1);
        formPanel.add(tfPlayer1);
        formPanel.add(lbPlayer2);
        formPanel.add(tfPlayer2);


        JPanel mainPanel= new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128, 128, 255));


        setTitle("WELCOME");
        setSize(500, 600);
        setMaximumSize(new Dimension(300,400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


}