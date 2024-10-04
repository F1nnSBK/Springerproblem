package schachbrett;

public class Springer {
    static int N = Schachbrett.N;

    // Konstruktor Springer() --> ruft Methode xx() auf
    public Springer() {
        // 2D Array als Schachbrett
        int[][] board = new int[N][N];
        System.out.println("Größe des Schachfelds: "+ board.length+"x"+ board.length);
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                board[i][j] = -1;
            }
        }
        springerBewegungen(board);
    }

    // main-Methode --> Springer() Konstruktor --> xx() Methode
    public static void main(String[] args) {
        new Springer();
    }

    private void springerBewegungen(int[][] board) {
        int[] xMoves = {1,2,2,1,-1,-2,-2,-1};
        int[] yMoves = {-2,-1,1,2,2,1,-1,-2};

        board[0][0] = 0;

        if(springerBewegungUtil(board, xMoves, yMoves, 0, 0, 1)) {
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
                if (arr[i][j]<10) {
                    System.out.println(arr[i][j]+"   ");
                }
                else {
                    System.out.println(arr[i][j]+"  ");
                }
            }
            System.out.println();
        }
    }

    static boolean isValid(int[][] board, int row, int col) {
        return(row >= 0 && row < N && col >= 0 && col < N && board[row][col] == -1);
    }

    static boolean springerBewegungUtil(int[][] board, int[] xMoves, int[] yMoves, int row, int col, int moveCount) {
        if(moveCount == N*N) {
            return true;
        }
        int x,y;
        for(int i=0;i<N;i++) {
            x = row + xMoves[i];
            y = col + yMoves[i];

            if(isValid(board, x, y)) {
                board[x][y] = moveCount;

                if(springerBewegungUtil(board, xMoves, yMoves, x, y, moveCount+1)) {
                    return true;
                }
                else {
                    board[x][y] = -1;
                }
            }
        }
        return false;
    }

}
