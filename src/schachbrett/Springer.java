package schachbrett;

/**
 * Dieses Programm enthält die Logik hinter dem Springerproblem (Knight's tour) von Euler
 * Außerdem wird ein Array mit der Reihenfolge der Sprünge des Springers an die Schachbrettklasse
 * weitergegeben.
 * Als Ausgabe gibt es eine Tabelle, welche die Reihenfolge der Züge anzeigt.
 *
 * @author Finn Hertsch
 * @version 1.0.0
 */

public class Springer {
    // Erstellt eine Instanzvariable der Schachbrettgröße N aus Schachbrett.java
    static int N = Schachbrett.N;
    // Erstellt das 2D path Array basierend auf der Schachbrettgröße
    private static final int[][] path = new int[Schachbrett.N*Schachbrett.N][2];
    static int counter = 0;

    /**
     * Dient als Konstruktor
     * Initialisiert das board Array, welches das Schachbrett widerspiegelt,
     * Gibt die Größe des Schachfeldes aus und setzt alle Felder des Schachfeldes auf den Wert -1,
     * was bedeutet, dass alle Felder unbesucht sind. Abschließend wird die Funktion zur Berechnung
     * der Bewegungen des Springers aufgerufen.
     */
    public Springer() {
        // 2D Array als Schachbrett
        int[][] board = new int[N][N];
        // Gibt die Größe des Schachbretts aus
        System.out.println("Größe des Schachfelds: "+ board.length+"x"+ board.length);
        // Iteriert über alle Felder des boards und setzt sie auf -1, was bedeutet, dass es unbesucht ist
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                board[i][j] = -1;
            }
        }
        // Ruft die Methode springerBewegung() auf und gibt das board mit unbesuchten Feldern weiter
        springerBewegungen(board);
    }

    /**
     * Diese Methode getPath() gibt das path array weiter, wenn sie aufgerufen wird.
     *
     * @return path als 2D Array, enthält die Züge des Springers
     */
    public int[][] getPath() {
        return path;
    }

    /**
     * Main Methode, beschreibt die Commandline Befehle
     * Ruft die Methode Springer() auf.
     *
     * @param args zeigt, dass es sich in der Methode um die Commandline Argumente handelt, welche beim
     *             Starten des Programms ausgeführt werden.
     */
    public static void main(String[] args) {
        new Springer();
    }

    /**
     * Diese Methode dient als Driver-Methode (oder Wrapper-Methode), die die Springerbewegungen auf dem Schachbrett steuert.
     * Sie initialisiert die Startposition auf dem Schachbrett und die möglichen Züge des Springers.
     * Ruft die rekursive Methode springerBewegungUtil auf, um die Springerbewegungen auszuführen.
     *
     * @param board das 2D-Array, das das Schachbrett darstellt
     */
    private void springerBewegungen(int[][] board) {
        // Alle möglichen Züge des Springers → immer als Paar zu betrachten (x, y) 'L' Form
        int[] xMoves = {1,2,2,1,-1,-2,-2,-1};
        int[] yMoves = {-2,-1,1,2,2,1,-1,-2};

        // Setzt die Startposition 0
        board[0][0] = 0;
        // Speichert die Startposition im 2D Array
        path[0][0] = 0; // Zeilen
        path[0][1] = 0; // Spalten

        // Ruft die rekursive Methode springerBewegungUtil auf, um die Springerbewegungen auszuführen
        if(springerBewegungUtil(board, xMoves, yMoves, 0, 0, 1)) {
            // Wenn springerBewegungUtil eine Lösung findet, wird diese and springerLoesung() weitergegeben
            springerLoesung(board);

            
            System.out.println("Lösung gefunden");
        }
        else {
            System.out.println("Keine Lösung gefunden");
        }
    }

    /**
     * Diese Methode dient zur Ausgabe der Lösung im Format einer Tabelle
     * Gibt die Schritte des Springers mit korrekten Abständen in das Terminal aus.
     *
     * @param arr steht, für das board array und enthält den moveCount (Zugnummer)
     */
    static void springerLoesung(int[][] arr) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // Gibt den Schritt zur besseren Darstellung aus (Matrix) --> Nachvollziehbarkeit des Ergebnisses
                // Bei einstelligen Zahlen 3 Spaces bei dreistelligen 2 Spaces
                if (arr[i][j]<10) {
                    System.out.printf(arr[i][j]+"   ");
                }
                else {
                    System.out.printf(arr[i][j]+"  ");
                }
            }
            // Zeilenumbruch nach dem Druck aller Spalten einer Zeile
            System.out.println();
        }
    }

    /**
     * Diese Methode Prüft, ob der jeweilige Schritt des Springers gültig ist.
     * Ist der Zug innerhalb des Schachfeldes?
     * Ist das Feld unbesucht (Wert -1)?
     *
     * @param board Array, welches das Schachfeld darstellt
     * @param row Reihen auf dem Schachfeld
     * @param col Spalten auf dem Schachfeld
     * @return Gibt einen boolean Wert aus true/false, ob der Zug gültig ist oder nicht
     */
    static boolean isValid(int[][] board, int row, int col) {
        return(row >= 0 && row < N && col >= 0 && col < N && board[row][col] == -1);
    }

    /**
     * Diese Hilfsmethode (oder Utility-Methode) oder spez. Backtracking-Methode
     * berechnet den nächsten Schritt des Springers und geht einen Schritt zurück, wenn der
     * Schritt nicht gültig ist.
     * Dafür nutzt sie die Liste aller möglichen Bewegungen aus der Driver-Methode und
     * ruft sich selbst auf, um den nächsten Zug zu finden.
     *
     * @param board Array, welches das Schachbrett darstellt
     * @param xMoves Array, mit allen möglichen Bewegungen in x-Richtung
     * @param yMoves Array, mit allen möglichen Bewegungen in y-Richtung
     * @param row aktuelle Zeile
     * @param col aktuelle Spalte
     * @param moveCount aktuelle Nummer des Zuges
     * @see Springer().isValid() gibt an, ob der Zug gültig ist oder nicht
     * @return true, wenn die Anzahl aller Schritte erreicht ist und jedes Feld besucht wurde
     *         und false, wenn es keine weitere Lösung mehr gibt
     */
    static boolean springerBewegungUtil(int[][] board, int[] xMoves, int[] yMoves, int row, int col, int moveCount) {
        // Wenn die Anzahl der moves 64 erreicht hat, wird true weitergegeben, was das Programm stoppt
        if(moveCount == N*N) {
            return true;
        }
        // Variablen für die neuen Positionen des Springers
        int x,y;
        for(int i=0;i<N;i++) {
            // Berechnet die nächsten möglichen Züge
            x = row + xMoves[i];
            y = col + yMoves[i];

            // Überprüfung der neuen Position
            if(isValid(board, x, y)) {
                // Markiert das Feld mit der moveCount Nummer (Zugnummer)
                board[x][y] = moveCount;
                // Speichert den Zug im path Array
                path[moveCount][0] = x;
                path[moveCount][1] = y;

                // Rekursiver Aufruf der Methode um die nächten möglichen Schritte zu finden
                if(springerBewegungUtil(board, xMoves, yMoves, x, y, moveCount+1)) {
                    // Lösung gefunden → true, führt zur Speicherung der Lösung im board und path Array
                    return true;
                }
                else {
                    // Wenn der nächste Schritt nicht gültig ist, wird ein Schritt zurückgegangen (Backtracking)
                    board[x][y] = -1;

                    if (moveCount < (N*N)-1) {
                        System.out.println("Ich bin falsch gelaufen " + counter);
                        counter = counter + 1;
                    }
                }
            }
        }
        return false; // return false, falls es keine Lösung mehr gibt
    }

}
