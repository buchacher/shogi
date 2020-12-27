package animalchess;


/**
 * A class for the Shogi Game.
 * @version 1
 */
public class Game {
    static final int HEIGHT = 6;
    static final int WIDTH = 5;

    private Player p0, p1;
    private Square[][] gameBoard; // List not ArrayList because immutable

    /**
     * A constructor for the class Game.
     * @param p0 of type Player represents Player 1.
     * @param p1 of type Player represents Player 2.
     */
    public Game(Player p0, Player p1) {
        this.p0 = p0;
        this.p1 = p1;

        this.gameBoard = new Square[HEIGHT][WIDTH];
        buildGameBoard();
    }

    /**
     * A method to build the gameBoard for a Game.
     */
    public void buildGameBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i < 2) {
                    gameBoard[i][j] = new Square(this, i, j, p1);
                }
                else if (i > 3) {
                    gameBoard[i][j] = new Square(this, i, j, p0);
                }
                else {
                    gameBoard[i][j] = new Square(this, i, j);
                }
            }
        }
    }

    /**
     * A method to get an object of type Player given their playerNumber.
     * @param playerNumber of type int represents a Player's playerNumber.
     * @return playerNumber of type int
     */
    public Player getPlayer(int playerNumber) {
        if (playerNumber == 0) {
            return p0;
        }
        else if (playerNumber == 1) {
            return p1;
        }
        else {
            // TODO Short message describing what went wrong
            throw new IllegalArgumentException();
        }
    }


    /**
     * A method to get a Game's winner
     * @return Player p0 if they have won, Player p1 if they have won or null if Game is ongoing.
     */
    public Player getWinner() {
        // hasWon returns boolean, so - when called on a Player - can be used as condition in itself.
        if (p0.hasWon()) {
            return p0;
        }
        else if (p1.hasWon()) {
            return p1;
        }
        else {
            return null;
        }
    }

    /**
     * A method to get a Game's Square given coordinates.
     * @param row of type int denotes the gameBoard's y coordinate or row.
     * @param col of type int denotes the gameBoard's x coordinate of column.
     * @return gameBoard, 2d array of Squares
     */
    public Square getSquare(int row, int col) {
        return gameBoard[row][col];
    }

    /**
     * A method to assert that a Game's Square at coordinates row, col is not outwith gameBoard.
     * @param row of type int denotes the gameBoard's y coordinate or row.
     * @param col of type int denotes the gameBoard's x coordinate of column.
     * @return True if the Square at coordinates row, col is within gameBoard.
     */
    public boolean hasSquare(int row, int col) {
        // Returns true if the Square at coordinates row, col is not outwith gameBoard.
        return (row >= 0 && row < HEIGHT) && (col >= 0 && col < WIDTH); // Note to self: Can do this in Java.
    }
}
