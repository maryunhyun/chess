package chess;

import java.util.Arrays;

import static chess.ChessGame.TeamColor.BLACK;
import static chess.ChessGame.TeamColor.WHITE;
import static chess.ChessPiece.PieceType.*;

/**
 * A chessboard that can hold and rearrange chess pieces.
 *
 * This class stores all the uncaptured pieces in a Game. It needs to
 * support adding and removing pieces for testing, as well as a
 * resetBoard() method that sets the standard Chess starting configuration.
 *
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    public ChessPiece[][] squares = new ChessPiece[8][8];
    public ChessBoard() {
        
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        //can add as numbers you would naturally think
        squares[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow()-1][position.getColumn()-1];
    }
    public ChessPiece[][] getBoardSquares(){
        return squares;
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        ChessPiece tempPiece = new ChessPiece(BLACK, ROOK);
        //putting a rook on the top left and right for black team
        //tempPiece.setChessPieceColor(BLACK);
        //tempPiece.setChessType(ROOK);
        squares[7][0] = tempPiece;
        squares[7][7] = tempPiece;

        ChessPiece tempPiece1 = new ChessPiece(WHITE, ROOK);
        squares[0][0] = tempPiece1;
        squares[0][7] = tempPiece1;

        ChessPiece tempPiece2 = new ChessPiece(WHITE, KNIGHT);
        squares[0][1] = tempPiece2;
        squares[0][6] = tempPiece2;

        ChessPiece tempPiece3 = new ChessPiece(BLACK, KNIGHT);
        squares[7][1] = tempPiece3;
        squares[7][6] = tempPiece3;

        ChessPiece tempPiece4 = new ChessPiece(BLACK, BISHOP);
        squares[7][2] = tempPiece4;
        squares[7][5] = tempPiece4;

        ChessPiece tempPiece5 = new ChessPiece(WHITE, BISHOP);
        squares[0][2] = tempPiece5;
        squares[0][5] = tempPiece5;

        ChessPiece tempPiece6 = new ChessPiece(WHITE, KING);
        squares[0][4] = tempPiece6;

        ChessPiece tempPiece7 = new ChessPiece(WHITE, QUEEN);
        squares[0][3] = tempPiece7;

        ChessPiece tempPiece8 = new ChessPiece(BLACK, QUEEN);
        squares[7][3] = tempPiece8;

        ChessPiece tempPiece9 = new ChessPiece(BLACK, KING);
        squares[7][4] = tempPiece9;

        ChessPiece tempPiece10 = new ChessPiece(BLACK, PAWN);
        for (int i = 0; i < 8; i++) {
            squares[6][i] = tempPiece10;
        }

        ChessPiece tempPiece11 = new ChessPiece(WHITE, PAWN);
        for (int i = 0; i < 8; i++) {
            squares[1][i] = tempPiece11;
        }
    }




    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\"\"\"");
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j] != null) {
                    stringBuilder.append("|");
                    if (squares[i][j].getTeamColor() == BLACK && squares[i][j].getPieceType() == PAWN) {
                        stringBuilder.append("p");
                    }
                    else if (squares[i][j].getTeamColor() == BLACK && squares[i][j].getPieceType() == ROOK) {
                        stringBuilder.append("r");
                    }
                    else if (squares[i][j].getTeamColor() == BLACK && squares[i][j].getPieceType() == QUEEN) {
                        stringBuilder.append("q");
                    }
                    else if (squares[i][j].getTeamColor() == BLACK && squares[i][j].getPieceType() == KNIGHT) {
                        stringBuilder.append("n");
                    }
                    else if (squares[i][j].getTeamColor() == BLACK && squares[i][j].getPieceType() == BISHOP) {
                        stringBuilder.append("b");
                    }
                    else if (squares[i][j].getTeamColor() == BLACK && squares[i][j].getPieceType() == KING) {
                        stringBuilder.append("k");
                    }
                    else if (squares[i][j].getTeamColor() == WHITE) {
                        if (squares[i][j].getPieceType() == PAWN) {
                            stringBuilder.append("P");
                        }
                        else if (squares[i][j].getPieceType() == ROOK) {
                            stringBuilder.append("R");
                        }
                        else if (squares[i][j].getPieceType() == KNIGHT) {
                            stringBuilder.append("N");
                        }
                        else if (squares[i][j].getPieceType() == BISHOP) {
                            stringBuilder.append("B");
                        }
                        else if (squares[i][j].getPieceType() == QUEEN) {
                            stringBuilder.append("Q");
                        }
                        else if (squares[i][j].getPieceType() == KING) {
                            stringBuilder.append("K");
                        }

                    }
                    //stringBuilder.append(squares[i][j].getTeamColor());
                    //stringBuilder.append(",");
                    //stringBuilder.append(squares[i][j].getPieceType());
                }
                else {
                    stringBuilder.append("| ");
                }
            }
            stringBuilder.append("|");
            stringBuilder.append('\n');
        }
        stringBuilder.append("\"\"\"");
        return stringBuilder.toString();
    }
}
