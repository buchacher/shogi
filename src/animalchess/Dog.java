package animalchess;

import java.util.ArrayList;

/**
 * A class for the Shogi Dog, a subclass of Piece.
 * @version 1
 */
public class Dog extends Piece {

    /**
     * A constructor for the subclass Dog.
     * @param owner of type Player represents a Piece's owner.
     * @param square of type Square represents a Piece's square.
     */
    public Dog(Player owner, Square square) {
        super(owner, square);
    }

    /** Override of getLegalMoves in superclass Piece.
     * @return legalMoves of type ArrayList<Square>, the legal moves for a Piece.
     */
    @Override
    public ArrayList<Square> getLegalMoves() {
        return getLegalDogMoves();
        }
}
