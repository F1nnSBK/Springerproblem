package schachbrett;

import java.awt.*;
import javax.swing.*;

public class Schachbrett extends JPanel {
    public static final int N = 8;
    private int[][] path;

    public Schachbrett(int[][] path) {
        this.path = path;
        this.setPreferredSize(new Dimension(800, 800));
    }

    public void paintComponent(Graphics g) {
        boolean white = false;
        super.paintComponent(g);
        int size = getWidth() / N;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (white) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                white = !white;
                g.fillRect(col * size, row * size, size, size);
            }
            white = !white;
        }

        // Draw path
        g.setColor(Color.RED);
        for (int i = 0; i < N * N - 1; i++) {
            int x1 = path[i][1] * size + size / 2;
            int y1 = path[i][0] * size + size / 2;
            int x2 = path[i+1][1] * size + size / 2;
            int y2 = path[i+1][0] * size + size / 2;
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        int[][] path = new Springer().getPath();
        JFrame fenster = new JFrame("Schachbrett");
        Schachbrett board = new Schachbrett(path);
        fenster.getContentPane().add(board);
        fenster.pack();
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setVisible(true);
    }
}