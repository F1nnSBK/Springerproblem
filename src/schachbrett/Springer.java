package schachbrett;

public class Springer {
    // Erstellt eine Instanzvariable der Schachbrettgröße N aus Schachbrett.java
    static int N = Schachbrett.N;
    // Erstellt das 2D path Array basierend auf der Schachbrettgröße
    private static final int[][] path = new int[Schachbrett.N*Schachbrett.N][2];

    // Konstruktor Springer() --> ruft Methode springerBewegungen() auf
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

    // Wird in Schachbrett.java aufgerufen, um das path objekt zu bekommen
    public int[][] getPath() {
        return path;
    }

    // main-Methode --> Springer() Konstruktor --> springerBewegungen() Methode
    public static void main(String[] args) {
        new Springer();
    }

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

    // Prüft, ob der berechnete Schritt im Bereich des Schachbretts ist und ob dieses Feld unbesucht (-1) als Wert hat
    static boolean isValid(int[][] board, int row, int col) {
        return(row >= 0 && row < N && col >= 0 && col < N && board[row][col] == -1);
    }


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
                }
            }
        }
        return false; // return false, falls es keine Lösung mehr gibt
    }

}
