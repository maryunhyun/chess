package chess;

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
        //ChessMove tempChessMove = new ChessMove(myPosition,null, null);
        //a specific move the bishop can make of going to the diagonal one space up and to the left

        if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1] != null) {
            if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.BISHOP) {

                //increasing row and column
                int j = myPosition.getColumn();
                ChessPosition [] chessPositions1 = new ChessPosition[10];
                ChessMove [] chessMoves1 = new ChessMove[10];
                int z = 0;
                for (int i = myPosition.getRow()+1; i < 9; i++ ) {
                    j++;
                    if (j > 8) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions1[z] = new ChessPosition(i, j);
                        chessMoves1[z] = new ChessMove(myPosition, chessPositions1[z], null);
                        movesCollection.add(chessMoves1[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions1[z] = new ChessPosition(i, j);
                            chessMoves1[z] = new ChessMove(myPosition, chessPositions1[z], null);
                            movesCollection.add(chessMoves1[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }

                //increasing row and decreasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions2 = new ChessPosition[10];
                ChessMove [] chessMoves2 = new ChessMove[10];
                z = 0;
                for (int i = myPosition.getRow()+1; i < 9; i++ ) {
                    j--;
                    if (j < 1) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions2[z] = new ChessPosition(i, j);
                        chessMoves2[z] = new ChessMove(myPosition, chessPositions2[z], null);
                        movesCollection.add(chessMoves2[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions2[z] = new ChessPosition(i, j);
                            chessMoves2[z] = new ChessMove(myPosition, chessPositions2[z], null);
                            movesCollection.add(chessMoves2[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }
                //decreasing row and decreasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions3 = new ChessPosition[10];
                ChessMove [] chessMoves3 = new ChessMove[10];
                z = 0;
                for (int i = myPosition.getRow()-1; i > 0; i-- ) {
                    j--;
                    if (j < 1) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions3[z] = new ChessPosition(i, j);
                        chessMoves3[z] = new ChessMove(myPosition, chessPositions3[z], null);
                        movesCollection.add(chessMoves3[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions3[z] = new ChessPosition(i, j);
                            chessMoves3[z] = new ChessMove(myPosition, chessPositions3[z], null);
                            movesCollection.add(chessMoves3[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }

                //decreasing row and increasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions4 = new ChessPosition[10];
                ChessMove [] chessMoves4 = new ChessMove[10];
                z = 0;
                for (int i = myPosition.getRow()-1; i > 0; i-- ) {
                    j++;
                    if (j > 8) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions4[z] = new ChessPosition(i, j);
                        chessMoves4[z] = new ChessMove(myPosition, chessPositions4[z], null);
                        movesCollection.add(chessMoves4[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions4[z] = new ChessPosition(i, j);
                            chessMoves4[z] = new ChessMove(myPosition, chessPositions4[z], null);
                            movesCollection.add(chessMoves4[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }

            }
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.KING) {
                ChessPosition [] chessPositions5 = new ChessPosition[10];
                ChessMove [] chessMoves5 = new ChessMove[10];
                int i = myPosition.getRow();
                int j = myPosition.getColumn();
                int z = 0;

                //increasing row and column
                i++;
                j++;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions5[z] = new ChessPosition(i, j);
                        chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                        movesCollection.add(chessMoves5[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions5[z] = new ChessPosition(i, j);
                            chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                            movesCollection.add(chessMoves5[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                //increasing row
                i = myPosition.getRow();
                j = myPosition.getColumn();
                i++;

                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions5[z] = new ChessPosition(i, j);
                        chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                        movesCollection.add(chessMoves5[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions5[z] = new ChessPosition(i, j);
                            chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                            movesCollection.add(chessMoves5[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                //increasing row and decreasing column
                i = myPosition.getRow();
                j = myPosition.getColumn();
                i++;
                j--;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions5[z] = new ChessPosition(i, j);
                        chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                        movesCollection.add(chessMoves5[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions5[z] = new ChessPosition(i, j);
                            chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                            movesCollection.add(chessMoves5[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                //decreasing column
                i = myPosition.getRow();
                j = myPosition.getColumn();
                j--;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions5[z] = new ChessPosition(i, j);
                        chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                        movesCollection.add(chessMoves5[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions5[z] = new ChessPosition(i, j);
                            chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                            movesCollection.add(chessMoves5[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                //decreasing row and decreasing column
                i = myPosition.getRow();
                j = myPosition.getColumn();
                i--;
                j--;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions5[z] = new ChessPosition(i, j);
                        chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                        movesCollection.add(chessMoves5[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions5[z] = new ChessPosition(i, j);
                            chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                            movesCollection.add(chessMoves5[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                //decreasing row
                i = myPosition.getRow();
                j = myPosition.getColumn();
                i--;

                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions5[z] = new ChessPosition(i, j);
                        chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                        movesCollection.add(chessMoves5[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions5[z] = new ChessPosition(i, j);
                            chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                            movesCollection.add(chessMoves5[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                //decreasing row and increasing column
                i = myPosition.getRow();
                j = myPosition.getColumn();
                i--;
                j++;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions5[z] = new ChessPosition(i, j);
                        chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                        movesCollection.add(chessMoves5[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions5[z] = new ChessPosition(i, j);
                            chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                            movesCollection.add(chessMoves5[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                //increasing column
                i = myPosition.getRow();
                j = myPosition.getColumn();
                j++;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions5[z] = new ChessPosition(i, j);
                        chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                        movesCollection.add(chessMoves5[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions5[z] = new ChessPosition(i, j);
                            chessMoves5[z] = new ChessMove(myPosition, chessPositions5[z], null);
                            movesCollection.add(chessMoves5[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }
            }
        }
        //pieceMoves().add()
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
    public String toString() {
        return "ChessPiece{" +
                "chessPieceColor=" + chessPieceColor +
                ", chessType=" + chessType +
                '}';
    }
}
