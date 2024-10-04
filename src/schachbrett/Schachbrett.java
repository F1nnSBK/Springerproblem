package schachbrett;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Schachbrett {

    //private --> nur in dieser Klasse verfügbar

    // Größe des Schachfelds
    public static final int N = 8;

    // Konstruktor Schachbrett() --> ruft Methode launchGUI() auf
    public Schachbrett() {
        launchGUI();
    }

    // main-Methode --> Schachbrett() Konstruktor --> launchGUI() Methode
    public static void main(String[] args) {
        new Schachbrett();
    }

    // Erzeugt die Instanzvariable square des Typs JPanel (JPanel-Objekt)

    // private Methode ohne Rückgabewert (void) öffnet die GUI
    private void launchGUI() {
        // mainFrame Instanzvariable des Typs JFrame (JFrame-Objekt) --> Hauptfenster
        JFrame mainFrame;
        // Erstellt neue Instanz von JFrame mit dem Titel Schachbrett
        mainFrame = new JFrame("Schachbrett");
        // Größe dieses Fensters
        mainFrame.setSize(800, 600);
        // Setzt das Layout auf "Gridlayout" (AWT Layout Klasse)
        mainFrame.setLayout(new GridLayout(N, N));

        // Schachmuster erzeugen
        /*  iteriert über jedes der 64 Felder und prüft, ob das Feld weiß oder nicht weiß ist.
            Ist white wahr, wird das Feld weiß und der Wert der boolean Variable white wird umgekehrt.
            Das nächste Feld ist dann !white und bekommt durch den else Fall die Farbe Weiß.  */
        boolean white = true;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                if (white) {
                    square.setBackground(Color.WHITE);
                }
                else {
                    square.setBackground(Color.DARK_GRAY);
                }
                // Das square wird dem Hauptfenster zugeteilt, damit es dargestellt werden kann.
                mainFrame.add(square);
                white = !white;
            }
            white = !white;
        }

        // Beendet die Anwendung (System.exit()) wenn das Fenster geschlossen wird
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Verhindert das automatische Schließen des Fensters
        mainFrame.setVisible(true);
    }

}

