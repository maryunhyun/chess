package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    ChessGame.TeamColor chessPieceColor;
    ChessPiece.PieceType chessType;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        chessPieceColor = pieceColor;
        chessType = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return chessPieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return chessType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * This method is similar to ChessGame.validMoves, except it does not honor whose
     * turn it is or check if the king is being attacked. This method does account for
     * enemy and friendly pieces blocking movement paths. The pieceMoves method will need
     * to take into account the type of piece, and the location of other pieces on the board.
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //NEED TO CHANGE
        //Saying no moves
        return new ArrayList<>();
    }
}
