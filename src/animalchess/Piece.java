package animalchess;

import java.util.ArrayList;

/**
 * An abstract class for the Shogi Piece, a superclass to PromotablePiece, Dog and Lion.
 * @version 1
 */
public abstract class Piece {
    private Player owner;
    private Square square;

    /**
     * A constructor for the class Piece.
     * @param owner of type Player represents a Piece's owner.
     * @param square of type Square represents a Piece's square.
     */
    public Piece(Player owner, Square square) {
        this.owner = owner;
        this.square = square;
        // Square and Piece have the other as an attribute and are constructed with the other respectively,
        // so possible updating must be remembered to be done in both.
        this.square.placePiece(this);
    }

    /** An abstract method that subclasses of Piece should override - with the exception of subclasses of
     * PromotablePiece.
     * @return legalMoves of type ArrayList<Square>, the legal moves for a Piece.
     */
    public abstract ArrayList<Square> getLegalMoves();

    /**
     * A method to move a Piece to a Square.
     * @param toSquare of type Square denotes the Square to be moved to.
     */
    public void move(Square toSquare) {
        // TODO Validate legal moves?
        // TODO Check logic
        // If that Square is occupied by a Piece and that Piece's owner is not this Piece's owner.
        if (toSquare.isOccupied() && !toSquare.getPiece().getOwner().equals(owner)) {
            toSquare.getPiece().beCaptured(owner);
        }
        Game game = toSquare.getGame();
        // If the scenario involves a null Game, you cannot call getSquare, because there is no gameBoard,
        // so placePiece is called.
        if (game == null) {
            toSquare.placePiece(this);
        }
        else if (getLegalMoves().contains(toSquare)) {
            getSquare().setPiece(null);
            setSquare(toSquare);
            toSquare.setPiece(this);
        }
    }

    /**
     * A method for the capturing of a Piece by an opponent.
     * @param capturer of type Player denotes the Player capturing the Piece.
     */
    public void beCaptured(Player capturer) {
        this.setOwner(capturer);
        getSquare().setPiece(null);
        capturer.addPieceToHand(this);
        setSquare(null);
    }

    /**
     * A standard getter for square.
     * @return square of type Square
     */
    public Square getSquare() {
        return square;
    }

    /**
     * A standard setter for square.
     * @param square square of type Square
     */
    public void setSquare(Square square) {
        this.square = square;
    }

    /**
     * A standard getter for owner.
     * @return owner of type Player
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * A standard setter for owner.
     * @param owner of type Player
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Adds a Square to the given ArrayList (legalMoves) if the change in row and column lead to an existing Square
     * that is not occupied by a Piece of the same Player whose Piece is on the current Square.
     * Using protected to limit the accessibility to this and subclasses.
     * @param currentPosition of type Square represents the current position of the Piece.
     * @param rowMove of type int represents the row of Square to be evaluated.
     * @param colMove of type int represents the column of Square to be evaluated.
     * @param legalMoves of type ArrayList<Square> represents a list of legal moves.
     */
    protected void addMoveIfPossible(Square currentPosition, int rowMove, int colMove, ArrayList<Square> legalMoves) {
        Game game = currentPosition.getGame();
        Player owner = getOwner();
        int targetRow = currentPosition.getRow() + rowMove;
        int targetCol = currentPosition.getCol() + colMove;

        if (game.hasSquare(targetRow, targetCol)) { // Is not outwith gameBoard.
            // The target square being evaluated for a potential next move
            Square targetSquare = game.getSquare(targetRow, targetCol);
            if (targetSquare.isOccupied()) {
                Piece occupyingPiece = targetSquare.getPiece();
                if(owner.equals(occupyingPiece.getOwner())) { // If Pieces have the same owner
                    // If targetSquare is occupied by a Piece of of the same owner, the move is not legal as one
                    // cannot capture one's own Piece and move to the Square it's occupying respectively.
                    return; // Break out of loop
                }
            }

            legalMoves.add(game.getSquare(targetRow, targetCol));
        }
    }

    /**
     * A method to define legal moves for a Dog.
     * @return legalMoves of type ArrayList<Square>
     */
    // Using protected to limit the accessibility to this and subclasses.
    protected ArrayList<Square> getLegalDogMoves() {
        Square currentPosition = getSquare();
        Player owner = getOwner();

        // initialCapacity: 8, because Dog has ability to move in 6 directions.
        ArrayList<Square> legalMoves = new ArrayList<>(6);

        // Move forwards
        addMoveIfPossible(currentPosition, owner.getDirection(), 0, legalMoves);
        // TODO Check line+2 works fine
        // Move backwards; NOTE: A negate operator '-' is placed before owner.getDirection()
        addMoveIfPossible(currentPosition, -owner.getDirection(), 0, legalMoves);
        // Move right
        addMoveIfPossible(currentPosition, 0, 1, legalMoves);
        // Move left
        addMoveIfPossible(currentPosition, 0, -1, legalMoves);
        // Move diagonal right into players direction
        addMoveIfPossible(currentPosition, owner.getDirection(), 1, legalMoves);
        // Move diagonal left into players direction
        addMoveIfPossible(currentPosition, owner.getDirection(), -1, legalMoves);

        return legalMoves;
    }
}
