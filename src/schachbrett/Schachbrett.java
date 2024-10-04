package schachbrett;

import java.awt.*;
import javax.swing.*;

public class Schachbrett extends JPanel {
    // N als Variable der Schachfeldgröße
    public static final int N = 8;
    // Pfad path als 2D Array um die Reihenfolge der Schritte des Springers zu speichern
    private final int[][] path;
    // Konstruktor zur Initialisierung des Schachbrett-Objekts
    public Schachbrett(int[][] path) {
        // path Instanzvariable wird auf path 2D Array übergeben
        this.path = path;
        // Setzt die größe des Fensters
        this.setPreferredSize(new Dimension(800, 800));
    }

    // nutzt die paintComponent Methode von JPanel um das Schachbrett und den Pfad des Springers zu zeichnen
    public void paintComponent(Graphics g) {
        // Wird für die Schleife des Schachbrettmusters benötigt.
        boolean white = false;
        // Ruft die paint-Methode der parentclass JPanel auf um es neu zu starten
        super.paintComponent(g);
        // Bestimmt die Größe der Felder basierend auf der Höhe des Fensters
        int size = getHeight() / N;
        // Erzeugt das Schachbrettmuster
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (white) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                // boolean toggle
                white = !white;
                // Zeichnet das Schachbrett basierend auf der Größe des Fensters
                g.fillRect(col * size, row * size, size, size);
            }
            white = !white;
        }

        // Zeichnet den Pfad des Springers für alle Schritte 0-63
        g.setColor(Color.RED);
        for (int i = 0; i < N * N - 1; i++) {
            // Berechnung der Koordinaten des 'i'-ten Feldes (Koordinate in der Mitte des Feldes)
            int x1 = path[i][1] * size + size / 2;
            int y1 = path[i][0] * size + size / 2;
            // Berechnung der Koordinaten des 'i+1'-ten Feldes (Koordinate in der Mitte des Feldes)
            int x2 = path[i+1][1] * size + size / 2;
            int y2 = path[i+1][0] * size + size / 2;
            /* Zeichnet eine Linie zwischen P1(x1,y1) & P2(x2,y2)
            *  P1 --> 'i'-tes Feld bis P2 --> 'i+1'-tes Feld */
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        // importiert den path mit den Schritten des Springers aus der Springer() Klasse
        int[][] path = new Springer().getPath();
        // Erstellt das Hauptfenster mit dem Titel Schachbrett
        JFrame fenster = new JFrame("Schachbrett");
        // Übergibt das pathArray an den Konstruktor
        Schachbrett board = new Schachbrett(path);
        // Fügt das Schachbrett dem Fenster zu
        fenster.getContentPane().add(board);
        // Passt die Größe des Fensters auf Basis der bevorzugten Größe aus dem Konstruktor an
        fenster.pack();
        // Regelt das Verhalten beim Schließen des Fensters
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Verhindert das automatische Schließen des Fensters und lässt es sichtbar
        fenster.setVisible(true);
    }
}