package animalchess;

import java.util.ArrayList;

/**
 * An abstract class for the Shogi PromotablePiece, a subclass of Piece and superclass to Cat and Chick.
 * @version 1
 */
public abstract class PromotablePiece extends Piece {
    private boolean promoted;

    /**
     * A constructor for the subclass PromotablePiece.
     * @param owner of type Player represents a Piece's owner.
     * @param square of type Square represents a Piece's square.
     */
    public PromotablePiece(Player owner, Square square) {
        super(owner, square);
        this.promoted = false;
    }

    /**
     * A method to assert whether a Piece has been promoted.
     * @return true of Piece has been promoted.
     */
    public boolean getIsPromoted() {
            return promoted; // Boolean... see setPromoted
    }

    /** A setter for a Piece's promoted attribute, which is set to true.
     * @param promoted of type boolean
     */
    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    /**
     * A method to promote a Piece.
     */
    public void promote() {
        this.setPromoted(true);
    }

    /** An abstract to define a PromotablePiece's legal moves when it is not promoted.
     * @return legalMoves of type ArrayList<Square>, legal next moves for a Piece.
     */
    protected abstract ArrayList<Square> getUnpromotedMoves();

    /**
     * If getLegalMoves is called on a Cat or Chick, because they are subclasses of PromotablePiece,
     * if they have been promoted and therefore move like a Dog, getLegalDogMoves will be called,
     * if not, getUnpromotedMoves will be called, in which the legal moves for an unpromoted Cat or Chick
     * are defined in the respective subclasses.
     * Final because subclasses of PromotablePiece should override getUnpromotedMoves instead.
     * @return legalMoves of type ArrayList<Square>, legal next moves for a Piece.
     */
    @Override
    public final ArrayList<Square> getLegalMoves() {
        if (promoted) {
            return getLegalDogMoves();
        }
        return getUnpromotedMoves();
    }

    /**
     * Override of beCaptured in Piece because if a PromotedPiece is captured, it is unpromoted.
     * @param capturer of type Player denotes the Player capturing the Piece.
     */
    @Override
    public void beCaptured(Player capturer) {
        super.beCaptured(capturer); // Everything that is defined in beCaptured in superclass Piece
        setPromoted(false);

    }

    /**
     * Override of move in Piece because PromotablePiece, if moved to Square in this Player's promotion zone,
     * is promoted.
     * @param toSquare of type Square denotes the Square to be moved to.
     */
    @Override
    public void move(Square toSquare) {
        super.move((toSquare)); // Everything that is defined in move in superclass Piece
        if (toSquare.isPromotionZone(getOwner())) {
            setPromoted(true);
        }
    }
}
