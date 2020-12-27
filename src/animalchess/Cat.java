package animalchess;

import java.util.ArrayList;

/**
 * A class for the Shogi Cat, a subclass of PromotablePiece.
 * @version 1
 */
public class Cat extends PromotablePiece {

    /**
     * A constructor for the subclass Cat.
     * @param owner of type Player represents a Piece's owner.
     * @param square of type Square represents a Piece's square.
     */
    public Cat(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * Override of getUnpromotedMoves in superclass PromotablePiece. If promoted, Cat behaves like Dog.
     * @returnlegalMoves of type ArrayList<Square>, legal next moves for a Piece.
     */
    @Override
    public ArrayList<Square> getUnpromotedMoves() {
        Square currentPosition = getSquare();
        Player owner = getOwner();

        // initialCapacity: 5, because Cat has ability to move in 5 directions.
        ArrayList<Square> legalMoves = new ArrayList<>(5);

        // Move forwards
        addMoveIfPossible(currentPosition, owner.getDirection(), 0, legalMoves);
        // Move diagonal left up
        addMoveIfPossible(currentPosition, -1, -1, legalMoves);
        // Move diagonal left down
        addMoveIfPossible(currentPosition, 1, -1, legalMoves);
        // Move diagonal right up
        addMoveIfPossible(currentPosition, -1, 1, legalMoves);
        // Move diagonal right down
        addMoveIfPossible(currentPosition, 1, 1, legalMoves);

        return legalMoves;
    }
}
