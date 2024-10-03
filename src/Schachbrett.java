
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Schachbrett {
    private JFrame mainFrame;

    public Schachbrett() {
        launchGUI();
    }

    public static void main(String[] args) {
        Schachbrett swingSchachbrett = new Schachbrett();
    }

    private void launchGUI() {
        mainFrame = new JFrame("Schachbrett");
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new GridLayout(8, 8));

        // Schachmuster erzeugen

        boolean white = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                if (white) {
                    square.setBackground(Color.DARK_GRAY);
                }
                else {
                    square.setBackground(Color.WHITE);
                }
                mainFrame.add(square);
                white = !white;
            }
            white = !white;
        }

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.setVisible(true);
    }

}