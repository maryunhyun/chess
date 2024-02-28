package chess;

import java.util.HashSet;

public class Queen {
    public HashSet<ChessMove> queenMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> movesCollection) {
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
                }
                break;
            }
        }
        return movesCollection;
    }
}
