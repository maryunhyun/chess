package chess;

import java.util.Objects;

/**
 * Represents moving a chess piece on a chessboard
 *
 * This class represents a possible move a chess piece could make.
 * It contains the starting and ending positions. It also contains a
 * field for the type of piece a pawn is being promoted to. If the
 * move would not result in a pawn being promoted, the promotion type field should be null.
 *
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {
    ChessPosition chessStartPosition;
    ChessPosition chessEndPosition;
    ChessPiece.PieceType chessPromotionPiece;
    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.chessStartPosition = startPosition;
        this.chessEndPosition = endPosition;
        this.chessPromotionPiece = promotionPiece;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return chessStartPosition;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return chessEndPosition;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMove chessMove = (ChessMove) o;
        return Objects.equals(chessStartPosition, chessMove.chessStartPosition) && Objects.equals(chessEndPosition, chessMove.chessEndPosition) && chessPromotionPiece == chessMove.chessPromotionPiece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chessStartPosition, chessEndPosition, chessPromotionPiece);
    }

    @Override
    public String toString() {
        return "ChessMove{" +
                "chessStartPosition=" + chessStartPosition +
                ", chessEndPosition=" + chessEndPosition +
                ", chessPromotionPiece=" + chessPromotionPiece +
                '}';
    }
}
