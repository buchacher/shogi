package animalchess;

import java.util.ArrayList;

/**
 * A class for the Shogi Chick, a subclass of PromotablePiece.
 * @version 1
 */
public class Chick extends PromotablePiece {

    /**
     * A constructor for the subclass Chick.
     * @param owner of type Player represents a Piece's owner.
     * @param square of type Square represents a Piece's square.
     */
    public Chick(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * Override of getUnpromotedMoves in superclass PromotablePiece. If promoted, Chick behaves like Dog.
     * @returnlegalMoves of type ArrayList<Square>, legal next moves for a Piece.
     */
    @Override
    public ArrayList<Square> getUnpromotedMoves() {
        Square currentPosition = getSquare();

        // initialCapacity: 5, because Cat has ability to move in 5 directions.
        ArrayList<Square> legalMoves = new ArrayList<>(1);

        addMoveIfPossible(currentPosition, getOwner().getDirection(), 0, legalMoves);

        // Method goal
        return legalMoves;
    }
}
