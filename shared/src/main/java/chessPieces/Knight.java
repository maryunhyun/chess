package chessPieces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public class Knight {
    public HashSet<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> movesCollection) {
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
                    z++;
                }
            }
        }
        return movesCollection;
    }
}
