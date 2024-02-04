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
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.KNIGHT) {
                ChessPosition [] chessPositions6 = new ChessPosition[10];
                ChessMove [] chessMoves6 = new ChessMove[10];

                int z = 0;

                //increasing row
                int i = myPosition.getRow();
                int j = myPosition.getColumn();
                i = i + 2;

                j++;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions6[z] = new ChessPosition(i, j);
                        chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                        movesCollection.add(chessMoves6[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions6[z] = new ChessPosition(i, j);
                            chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                            movesCollection.add(chessMoves6[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                j = myPosition.getColumn();
                j--;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions6[z] = new ChessPosition(i, j);
                        chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                        movesCollection.add(chessMoves6[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions6[z] = new ChessPosition(i, j);
                            chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                            movesCollection.add(chessMoves6[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                //decreasing row
                i = myPosition.getRow();
                j = myPosition.getColumn();
                i = i - 2;

                j++;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions6[z] = new ChessPosition(i, j);
                        chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                        movesCollection.add(chessMoves6[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions6[z] = new ChessPosition(i, j);
                            chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                            movesCollection.add(chessMoves6[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                j = myPosition.getColumn();
                j--;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions6[z] = new ChessPosition(i, j);
                        chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                        movesCollection.add(chessMoves6[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions6[z] = new ChessPosition(i, j);
                            chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                            movesCollection.add(chessMoves6[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }


                //increasing column
                i = myPosition.getRow();
                j = myPosition.getColumn();
                j = j + 2;

                i++;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions6[z] = new ChessPosition(i, j);
                        chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                        movesCollection.add(chessMoves6[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions6[z] = new ChessPosition(i, j);
                            chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                            movesCollection.add(chessMoves6[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                i = myPosition.getRow();
                i--;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions6[z] = new ChessPosition(i, j);
                        chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                        movesCollection.add(chessMoves6[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions6[z] = new ChessPosition(i, j);
                            chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                            movesCollection.add(chessMoves6[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                //decreasing column
                i = myPosition.getRow();
                j = myPosition.getColumn();
                j = j - 2;

                i++;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions6[z] = new ChessPosition(i, j);
                        chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                        movesCollection.add(chessMoves6[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions6[z] = new ChessPosition(i, j);
                            chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                            movesCollection.add(chessMoves6[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }

                i = myPosition.getRow();
                i--;
                if (j < 9 && j > 0 && i < 9 && i > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] == null) {
                        chessPositions6[z] = new ChessPosition(i, j);
                        chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                        movesCollection.add(chessMoves6[z]);
                        z++;
                    } else {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            chessPositions6[z] = new ChessPosition(i, j);
                            chessMoves6[z] = new ChessMove(myPosition, chessPositions6[z], null);
                            movesCollection.add(chessMoves6[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                            z++;
                        }
                    }
                }
            }
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.PAWN) {
                ChessPosition [] chessPositions7 = new ChessPosition[10];
                ChessMove [] chessMoves7 = new ChessMove[10];

                int z = 0;
                int i = myPosition.getRow();
                int j = myPosition.getColumn();
                if (myPosition.getRow() == 7 && board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor() == ChessGame.TeamColor.BLACK) {
                    //move like first move for black piece

                    //move two spaces
                    i = i - 2;
                    if (j < 9 && j > 0 && i < 9 && i > 0) {
                        if (board.getBoardSquares()[i - 1][j - 1] == null && board.getBoardSquares()[i][j-1] == null){
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                        }
                            //checking for enemies in diagonal

                            j--;
                            if (j > 0) {
                                if (board.getBoardSquares()[i - 1][j - 1] != null) {
                                    if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        //to move piece in
                                        //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                                        z++;
                                    }
                                }
                            }
                            j = j+2;
                            if (j < 9) {
                                if (board.getBoardSquares()[i - 1][j - 1] != null) {
                                    if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        //to move piece in
                                        //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                                        z++;
                                    }
                                }
                            }
                            i = myPosition.getRow();
                            j = myPosition.getColumn();

                    }

                    //move one space
                    i = myPosition.getRow();
                    i = i - 1;
                    if (j < 9 && j > 0 && i < 9 && i > 0) {
                        if (board.getBoardSquares()[i - 1][j - 1] == null) {
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                        }
                            //checking for enemies in diagonal

                            j--;
                            if (j > 0) {
                                if (board.getBoardSquares()[i - 1][j - 1] != null) {
                                    if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        //to move piece in
                                        //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                                        z++;
                                    }
                                }
                            }
                            j = j+2;
                            if (j < 9) {
                                if (board.getBoardSquares()[i - 1][j - 1] != null) {
                                    if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        //to move piece in
                                        //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                                        z++;
                                    }
                                }
                            }
                            i = myPosition.getRow();
                            j = myPosition.getColumn();

                    }

                }
                else if (myPosition.getRow() == 2 && board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor() == ChessGame.TeamColor.WHITE) {
                    //move like first move for white piece

                    //move two spaces
                    i = i + 2;
                    if (j < 9 && j > 0 && i < 9 && i > 0) {
                        if (board.getBoardSquares()[i - 1][j - 1] == null && board.getBoardSquares()[i - 2][j - 1] == null) {
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                        }
                            //checking for enemies in diagonal

                            j++;
                            if (j < 9) {
                                if (board.getBoardSquares()[i - 1][j - 1] != null) {
                                    if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        //to move piece in
                                        //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                                        z++;
                                    }
                                }
                            }
                            j = j-2;
                            if (j > 0) {
                                if (board.getBoardSquares()[i - 1][j - 1] != null) {
                                    if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        //to move piece in
                                        //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                                        z++;
                                    }
                                }
                            }
                            i = myPosition.getRow();
                            j = myPosition.getColumn();

                    }

                    //move one space
                    i = myPosition.getRow();
                    i = i + 1;
                    if (j < 9 && j > 0 && i < 9 && i > 0) {
                        if (board.getBoardSquares()[i - 1][j - 1] == null) {
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                        }
                            //checking for enemies in diagonal

                            j++;
                            if (j < 9) {
                                if (board.getBoardSquares()[i - 1][j - 1] != null) {
                                    if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        //to move piece in
                                        //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                                        z++;
                                    }
                                }
                            }
                            j = j-2;
                            if (j > 0) {
                                if (board.getBoardSquares()[i - 1][j - 1] != null) {
                                    if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        //to move piece in
                                        //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                                        z++;
                                    }
                                }
                            }
                            i = myPosition.getRow();
                            j = myPosition.getColumn();

                    }
                }

                //make sure moving forward in correct direction depending on color
                else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor() == ChessGame.TeamColor.BLACK){
                    i--;
                    if (j < 9 && j > 0 && i < 9 && i > 0) {
                        if (board.getBoardSquares()[i - 1][j - 1] == null) {
                            //promotion if reach end of board
                            if (i == 1) {
                                //get chosen promotion option and insert
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.BISHOP);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.KNIGHT);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.QUEEN);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.ROOK);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                            }
                            else {
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                            }
                        }
                            //checking for enemies in diagonal

                            j--;
                            if (board.getBoardSquares()[i-1][j-1] != null) {
                                if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                    //promotion if reach end of board
                                    if (i == 1) {
                                        //get chosen promotion option and insert
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.BISHOP);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.KNIGHT);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.QUEEN);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.ROOK);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                    }
                                    else {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                    }
                                }
                            }
                            j = j+2;
                            if (board.getBoardSquares()[i-1][j-1] != null) {
                                if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                    //promotion if reach end of board
                                    if (i == 1) {
                                        //get chosen promotion option and insert
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.BISHOP);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.KNIGHT);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.QUEEN);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.ROOK);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                    }
                                    else {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                    }
                                }
                            }
                            i = myPosition.getRow();
                            j = myPosition.getColumn();

                    }
                }

                else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor() == ChessGame.TeamColor.WHITE){
                    i++;
                    if (j < 9 && j > 0 && i < 9 && i > 0) {
                        if (board.getBoardSquares()[i - 1][j - 1] == null) {
                            //promotion if reach end of board
                            if (i == 8) {
                                //get chosen promotion option and insert
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.BISHOP);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.KNIGHT);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.QUEEN);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.ROOK);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                            }
                            else {
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                            }
                        }
                            //checking for enemies in diagonal

                            j++;
                            if (board.getBoardSquares()[i-1][j-1] != null) {
                                if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                    //promotion if reach end of board
                                    if (i == 8) {
                                        //get chosen promotion option and insert
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.BISHOP);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.KNIGHT);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.QUEEN);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.ROOK);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                    }
                                    else {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                    }
                                }
                            }
                            j = j-2;
                            if (board.getBoardSquares()[i-1][j-1] != null) {
                                if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                                    //promotion if reach end of board
                                    if (i == 8) {
                                        //get chosen promotion option and insert
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.BISHOP);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.KNIGHT);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.QUEEN);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], PieceType.ROOK);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                    }
                                    else {
                                        chessPositions7[z] = new ChessPosition(i, j);
                                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                        movesCollection.add(chessMoves7[z]);
                                        z++;
                                    }
                                }
                            }
                            i = myPosition.getRow();
                            j = myPosition.getColumn();

                    }
                }
            }
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.QUEEN) {
                //moving like ROOK

                //increasing row
                int j = myPosition.getColumn();
                ChessPosition [] chessPositions12 = new ChessPosition[10];
                ChessMove [] chessMoves12 = new ChessMove[10];
                int z = 0;
                for (int i = myPosition.getRow()+1; i < 9; i++ ) {

                    if (j > 8) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions12[z] = new ChessPosition(i, j);
                        chessMoves12[z] = new ChessMove(myPosition, chessPositions12[z], null);
                        movesCollection.add(chessMoves12[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions12[z] = new ChessPosition(i, j);
                            chessMoves12[z] = new ChessMove(myPosition, chessPositions12[z], null);
                            movesCollection.add(chessMoves12[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }

                //decreasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions13 = new ChessPosition[10];
                ChessMove [] chessMoves13 = new ChessMove[10];
                z = 0;
                for (int i = 0; i < 9; i++ ) {
                    j--;
                    if (j < 1) {
                        break;
                    }
                    else if (board.getBoardSquares()[myPosition.getRow()-1][j-1] == null) {
                        chessPositions13[z] = new ChessPosition(myPosition.getRow(), j);
                        chessMoves13[z] = new ChessMove(myPosition, chessPositions13[z], null);
                        movesCollection.add(chessMoves13[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[myPosition.getRow()-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions13[z] = new ChessPosition(myPosition.getRow(), j);
                            chessMoves13[z] = new ChessMove(myPosition, chessPositions13[z], null);
                            movesCollection.add(chessMoves13[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }
                //decreasing row
                j = myPosition.getColumn();
                ChessPosition [] chessPositions14 = new ChessPosition[10];
                ChessMove [] chessMoves14 = new ChessMove[10];
                z = 0;
                for (int i = myPosition.getRow()-1; i > 0; i-- ) {

                    if (j < 1) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions14[z] = new ChessPosition(i, j);
                        chessMoves14[z] = new ChessMove(myPosition, chessPositions14[z], null);
                        movesCollection.add(chessMoves14[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions14[z] = new ChessPosition(i, j);
                            chessMoves14[z] = new ChessMove(myPosition, chessPositions14[z], null);
                            movesCollection.add(chessMoves14[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }

                //increasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions15 = new ChessPosition[10];
                ChessMove [] chessMoves15 = new ChessMove[10];
                z = 0;
                for (int i = 9; i > 0; i-- ) {
                    j++;
                    if (j > 8) {
                        break;
                    }
                    else if (board.getBoardSquares()[myPosition.getRow()-1][j-1] == null) {
                        chessPositions15[z] = new ChessPosition(myPosition.getRow(), j);
                        chessMoves15[z] = new ChessMove(myPosition, chessPositions15[z], null);
                        movesCollection.add(chessMoves15[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[myPosition.getRow()-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions15[z] = new ChessPosition(myPosition.getRow(), j);
                            chessMoves15[z] = new ChessMove(myPosition, chessPositions15[z], null);
                            movesCollection.add(chessMoves15[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }
                //moving like BISHOP

                //increasing row and column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions16 = new ChessPosition[10];
                ChessMove [] chessMoves16 = new ChessMove[10];
                z = 0;
                for (int i = myPosition.getRow()+1; i < 9; i++ ) {
                    j++;
                    if (j > 8) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions16[z] = new ChessPosition(i, j);
                        chessMoves16[z] = new ChessMove(myPosition, chessPositions16[z], null);
                        movesCollection.add(chessMoves16[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions16[z] = new ChessPosition(i, j);
                            chessMoves16[z] = new ChessMove(myPosition, chessPositions16[z], null);
                            movesCollection.add(chessMoves16[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }

                //increasing row and decreasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions17 = new ChessPosition[10];
                ChessMove [] chessMoves17 = new ChessMove[10];
                z = 0;
                for (int i = myPosition.getRow()+1; i < 9; i++ ) {
                    j--;
                    if (j < 1) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions17[z] = new ChessPosition(i, j);
                        chessMoves17[z] = new ChessMove(myPosition, chessPositions17[z], null);
                        movesCollection.add(chessMoves17[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions17[z] = new ChessPosition(i, j);
                            chessMoves17[z] = new ChessMove(myPosition, chessPositions17[z], null);
                            movesCollection.add(chessMoves17[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }
                //decreasing row and decreasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions18 = new ChessPosition[10];
                ChessMove [] chessMoves18 = new ChessMove[10];
                z = 0;
                for (int i = myPosition.getRow()-1; i > 0; i-- ) {
                    j--;
                    if (j < 1) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions18[z] = new ChessPosition(i, j);
                        chessMoves18[z] = new ChessMove(myPosition, chessPositions18[z], null);
                        movesCollection.add(chessMoves18[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions18[z] = new ChessPosition(i, j);
                            chessMoves18[z] = new ChessMove(myPosition, chessPositions18[z], null);
                            movesCollection.add(chessMoves18[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }

                //decreasing row and increasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions19 = new ChessPosition[10];
                ChessMove [] chessMoves19 = new ChessMove[10];
                z = 0;
                for (int i = myPosition.getRow()-1; i > 0; i-- ) {
                    j++;
                    if (j > 8) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions19[z] = new ChessPosition(i, j);
                        chessMoves19[z] = new ChessMove(myPosition, chessPositions19[z], null);
                        movesCollection.add(chessMoves19[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions19[z] = new ChessPosition(i, j);
                            chessMoves19[z] = new ChessMove(myPosition, chessPositions19[z], null);
                            movesCollection.add(chessMoves19[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }
            }
            else if (board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getPieceType() == PieceType.ROOK) {
                //increasing row
                int j = myPosition.getColumn();
                ChessPosition [] chessPositions8 = new ChessPosition[10];
                ChessMove [] chessMoves8 = new ChessMove[10];
                int z = 0;
                for (int i = myPosition.getRow()+1; i < 9; i++ ) {

                    if (j > 8) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions8[z] = new ChessPosition(i, j);
                        chessMoves8[z] = new ChessMove(myPosition, chessPositions8[z], null);
                        movesCollection.add(chessMoves8[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions8[z] = new ChessPosition(i, j);
                            chessMoves8[z] = new ChessMove(myPosition, chessPositions8[z], null);
                            movesCollection.add(chessMoves8[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }

                //decreasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions9 = new ChessPosition[10];
                ChessMove [] chessMoves9 = new ChessMove[10];
                z = 0;
                for (int i = 0; i < 9; i++ ) {
                    j--;
                    if (j < 1) {
                        break;
                    }
                    else if (board.getBoardSquares()[myPosition.getRow()-1][j-1] == null) {
                        chessPositions9[z] = new ChessPosition(myPosition.getRow(), j);
                        chessMoves9[z] = new ChessMove(myPosition, chessPositions9[z], null);
                        movesCollection.add(chessMoves9[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[myPosition.getRow()-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions9[z] = new ChessPosition(myPosition.getRow(), j);
                            chessMoves9[z] = new ChessMove(myPosition, chessPositions9[z], null);
                            movesCollection.add(chessMoves9[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }
                //decreasing row
                j = myPosition.getColumn();
                ChessPosition [] chessPositions10 = new ChessPosition[10];
                ChessMove [] chessMoves10 = new ChessMove[10];
                z = 0;
                for (int i = myPosition.getRow()-1; i > 0; i-- ) {

                    if (j < 1) {
                        break;
                    }
                    else if (board.getBoardSquares()[i-1][j-1] == null) {
                        chessPositions10[z] = new ChessPosition(i, j);
                        chessMoves10[z] = new ChessMove(myPosition, chessPositions10[z], null);
                        movesCollection.add(chessMoves10[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[i-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions10[z] = new ChessPosition(i, j);
                            chessMoves10[z] = new ChessMove(myPosition, chessPositions10[z], null);
                            movesCollection.add(chessMoves10[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
                    }
                }

                //increasing column
                j = myPosition.getColumn();
                ChessPosition [] chessPositions11 = new ChessPosition[10];
                ChessMove [] chessMoves11 = new ChessMove[10];
                z = 0;
                for (int i = 9; i > 0; i-- ) {
                    j++;
                    if (j > 8) {
                        break;
                    }
                    else if (board.getBoardSquares()[myPosition.getRow()-1][j-1] == null) {
                        chessPositions11[z] = new ChessPosition(myPosition.getRow(), j);
                        chessMoves11[z] = new ChessMove(myPosition, chessPositions11[z], null);
                        movesCollection.add(chessMoves11[z]);
                        z++;
                    }
                    else {
                        if (board.getBoardSquares()[myPosition.getRow()-1][j-1].getTeamColor() != board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1].getTeamColor()) {
                            chessPositions11[z] = new ChessPosition(myPosition.getRow(), j);
                            chessMoves11[z] = new ChessMove(myPosition, chessPositions11[z], null);
                            movesCollection.add(chessMoves11[z]);
                            //to move piece in
                            //board.getBoardSquares()[i-1][j-1] = board.getBoardSquares()[myPosition.getRow()-1][myPosition.getColumn()-1];
                        }
                        break;
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
