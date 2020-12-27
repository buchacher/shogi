package animalchess;

import java.util.Objects;

/**
 * A class for the Shogi Square.
 * @version 1
 */
public class Square {
    private Game game;
    private int row, col;
    // Square and Piece have the other as an attribute and are constructed with the other respectively,
    // so possible updating must be remembered to be done in both.
    private Piece piece;
    private Player promotesPlayer;

    /**
     * One of two constructors for the class Square. If it is not a null game, fillSquare() is called
     * to construct the gameBoard.
     * @param game of type Game represents the Game this Square is part of. Can be null.
     * @param row of type int denotes the gameBoard's y coordinate or row.
     * @param col of type int denotes the gameBoard's x coordinate of column.
     */
    public Square(Game game, int row, int col) {
        this.game = game;
        this.row = row;
        this.col = col;
        this.piece = null; // Default null

        if (game != null) {
            fillSquare();
        }
    }

    /**
     * Two of two constructors for the class Square. If it is not a null Game, fillSquare() is called to construct
     * the gameBoard. Compared with the first constructor, this has an additional parameter, promotesPlayer. This
     * constructor will be called for Squares with rows < 2 or >3, because these are promotion zones.
     * @param game of type Game represents the Game this Square is part of. Can be null.
     * @param row of type int denotes the gameBoard's y coordinate or row.
     * @param col of type int denotes the gameBoard's x coordinate of column.
     * @param promotesPlayer of type Player denotes the Player whose Piece is promoted when places on this Square.
     */
    public Square(Game game, int row, int col, Player promotesPlayer) {
        this.game = game;
        this.row = row;
        this.col = col;
        this.piece = null; // Default null
        this.promotesPlayer = promotesPlayer;

        if (game != null) {
            fillSquare();
        }
    }

    /**
     * A method to initialise Pieces and place them on their starting positions. Only called when if not a null Game.
     */
    private void fillSquare() {
        // Initialise Piece and place on starting position
        // p0 row 0
        if (row == 0) {
            if (col == 0) {
                piece = new Cat(game.getPlayer(0), this);
            }
            else if (col == 1) {
                piece = new Dog(game.getPlayer(0), this);
            }
            else if (col == 2) {
                piece = new Lion(game.getPlayer(0), this);
            }
            else if (col == 3) {
                piece = new Dog(game.getPlayer(0), this);
            }
            else {
                piece = new Cat(game.getPlayer(0), this);
            }
        }

        // p0 row 2
        if (row == 2) {
            if (col == 1) {
                piece = new Chick(game.getPlayer(0), this);
            }
            else if (col == 2) {
                piece = new Chick(game.getPlayer(0), this);
            }
            else if (col == 3) { // Because there is 2,0 and 2,4 - explicitly state that it's 2,3
                piece = new Chick(game.getPlayer(0), this);
            }
        }

        // p1 row 3
        if (row == 3) {
            if (col == 1) {
                piece = new Chick(game.getPlayer(1), this);
            }
            else if (col == 2) {
                piece = new Chick(game.getPlayer(1), this);
            }
            else if (col == 3) { // because there is 3,0 and 3,4 - explicitly state that it's 3,3
                piece = new Chick(game.getPlayer(1), this);
            }
        }

        // p1 row 5
        if (row == 5) {
            if (col == 0) {
                piece = new Cat(game.getPlayer(1), this);
            }
            else if (col == 1) {
                piece = new Dog(game.getPlayer(1), this);
            }
            else if (col == 2) {
                piece = new Lion(game.getPlayer(1), this);
            }
            else if (col == 3) {
                piece = new Dog(game.getPlayer(1), this);
            }
            else {
                piece = new Cat(game.getPlayer(1), this);
            }
        }
    }

    /**
     * A method to set a Square's piece attribute to a particular Piece, i.e. placing a Piece on a Square.
     * This is useful in a null Game when getSquare cannot be called because Game is null.
     * @param piece of type Piece denotes the Piece to be placed on this Square.
     */
    public void placePiece(Piece piece) {
        if (!isOccupied()) { // If not occupied
            setPiece(piece);
            piece.setSquare(this);
        }
        else {
            throw new IllegalArgumentException("This square is already occupied.");
        }
    }

    /**
     * A standard setter for a Square's piece attribute.
     * @param piece of type Piece denotes the Piece that is placed on this Square.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * A method to remove a Piece from a Square.
     */
    public void removePiece() {
        if (isOccupied()) {
            piece.setSquare(null);
            setPiece(null);
        }
    }

    /**
     * A method to a assert whether a Square is in a Player's promotion zone.
     * @param player of type Player denotes the player for which this is asserted.
     * @return true if the Square is in the Player's promotion zone.
     */
    public boolean isPromotionZone(Player player) {
        // TODO Make sure this works, was collapsed by IntelliJ.
        if (player.getPlayerNumber() == 0 && this.row > 3) {
            return true;
        }
        else {
            // If row 2 or 3, line below will evaluate to and return false.
            return player.getPlayerNumber() == 1 && this.row < 2; // Note to self: Can do this in Java.
        }
    }

    /**
     * A standard getter for Game.
     * @return game of type Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * A standard getter for Piece.
     * @return piece of type Piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     *A method to assert whether a Square is occupied by a Piece.
     * @return true if occupied.
     */
    public boolean isOccupied() {
        return piece != null;
    }

    /**
     * A standard getter for row.
     * @return row of type int
     */
    public int getRow() {
        return row;
    }

    /**
     * A standard getter for column.
     * @return col of type int
     */
    public int getCol() {
        return col;
    }

    /**
     * A method to assert one Square equals another.
     * @param o of type Object represents a Square.
     * @return true if the Square's are equal, false if not.
     */
    // Intellij's cmd+n function was used to autogenerate the following method.
    // Override because the superclass is Object.
    // Begin autogenerated code
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return row == square.row &&
                col == square.col;
    }
    // End autogenerated code
}
