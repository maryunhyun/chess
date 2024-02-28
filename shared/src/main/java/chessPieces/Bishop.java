package chessPieces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public class Bishop {
    public HashSet<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> movesCollection) {
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
                }
                break;
            }
        }//increasing row and decreasing column
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
                }
                break;
            }
        }//decreasing row and decreasing column
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
                }break;
            }
        }//decreasing row and increasing column
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
                }break;
            }
        }return movesCollection;
    }
}
