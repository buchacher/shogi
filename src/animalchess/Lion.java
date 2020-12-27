package animalchess;

import java.util.ArrayList;

/**
 * A class for the Shogi Lion, a subclass of Piece.
 * @version 1
 */
public class Lion extends Piece {

    /**
     * A constructor for the subclass Lion.
     * @param owner of type Player represents a Piece's owner.
     * @param square of type Square represents a Piece's square.
     */
    public Lion(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * Override of getLegalMoves in superclass Piece.
     * Because the Lion has the ability to move in all directions, which Player owns this Lion is irrelevant
     * for the purpose of evaluating its legal moves.
     * @return legalMoves of type ArrayList<Square>, the legal moves for a Piece.
     */
    @Override
    public ArrayList<Square> getLegalMoves() {
        Square currentPosition = getSquare();

        // initialCapacity: 5, because Lion has ability to move in 8 directions.
        ArrayList<Square> legalMoves = new ArrayList<>(8);

        for (int rowChange = -1; rowChange <= 1; rowChange++) {
            for (int colChange = -1; colChange <= 1; colChange++) {
                // When rowChange == 0 && colChange == 0, this is the Square this Lion is occupying,
                // so the move is not possible and accordingly, addMoveIfPossible will not add this Square.
                addMoveIfPossible(currentPosition, rowChange, colChange, legalMoves);
            }
        }
        return legalMoves;
    }

    /**
     * Override of beCaptured in superclass Piece, because if a Lion is captured, the capturing Player wins
     * the game.
     * @param capturer of type Player denotes the Player capturing the Piece.
     */
    @Override
    public void beCaptured(Player capturer) {
        super.beCaptured(capturer);
        capturer.winGame();
    }
}
