package chess;

import chessPieces.*;

import java.util.*;

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

    public void setChessPieceColor(ChessGame.TeamColor tempColor) {
        this.chessPieceColor = tempColor;
    }

    public void setChessType(ChessPiece.PieceType tempType) {
        this.chessType = tempType;
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
    public HashSet<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //pawn not promoted and need end positions
        HashSet<ChessMove> movesCollection = new HashSet<>();
        Bishop bishop = new Bishop();
        King king = new King();
        Queen queen = new Queen();
        Knight knight = new Knight();
        Pawn pawn = new Pawn();
        Rook rook = new Rook();
        if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1] != null) {
            if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.BISHOP) {
                for (ChessMove chessMove : bishop.bishopMoves(board,myPosition,movesCollection)) {
                    movesCollection.add(chessMove);
                }
            }
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.KING) {
                for (ChessMove chessMove : king.kingMoves(board,myPosition,movesCollection)) {
                    movesCollection.add(chessMove);
                }
            }
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.KNIGHT) {
                for (ChessMove chessMove : knight.knightMoves(board,myPosition,movesCollection)) {
                    movesCollection.add(chessMove);
                }
            }
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.PAWN) {
                for (ChessMove chessMove : pawn.pawnMoves(board,myPosition,movesCollection)) {
                    movesCollection.add(chessMove);
                }
            }
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.QUEEN) {
                for (ChessMove chessMove : queen.queenMoves(board,myPosition,movesCollection)) {
                    movesCollection.add(chessMove);
                }
            }
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.ROOK) {
                for (ChessMove chessMove : rook.rookMoves(board,myPosition,movesCollection)) {
                    movesCollection.add(chessMove);
                }
            }
        }
        return movesCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return chessPieceColor == that.chessPieceColor && chessType == that.chessType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chessPieceColor, chessType);
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "chessPieceColor=" + chessPieceColor +
                ", chessType=" + chessType +
                '}';
    }
}
