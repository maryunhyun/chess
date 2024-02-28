package chessPieces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public class King {
    public HashSet<ChessMove> kingMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> movesCollection) {
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
                }
            }
        }
        return movesCollection;
    }
}
