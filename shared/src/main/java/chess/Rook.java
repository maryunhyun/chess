package chess;

import java.util.HashSet;

public class Rook {
    public HashSet<ChessMove> rookMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> movesCollection) {
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
                }
                break;
            }
        }
        return movesCollection;
    }
}
