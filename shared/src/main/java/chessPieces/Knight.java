package chessPieces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public class Knight {
    public HashSet<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> movesCollection) {
        ChessPosition[] chessPositions6 = new ChessPosition[10];
        ChessMove[] chessMoves6 = new ChessMove[10];

        int z = 0;

        //increasing row
        int i = myPosition.getRow();
        int j = myPosition.getColumn();
        i = i + 2;

        j++;
        checkKnight(i, j, z, myPosition,board, chessPositions6, chessMoves6, movesCollection);

        j = myPosition.getColumn();
        j--;
        checkKnight(i, j, z, myPosition,board, chessPositions6, chessMoves6, movesCollection);
        //decreasing row
        i = myPosition.getRow();
        j = myPosition.getColumn();
        i = i - 2;

        j++;
        checkKnight(i, j, z, myPosition,board, chessPositions6, chessMoves6, movesCollection);
        j = myPosition.getColumn();
        j--;
        checkKnight(i, j, z, myPosition,board, chessPositions6, chessMoves6, movesCollection);
        //increasing column
        i = myPosition.getRow();
        j = myPosition.getColumn();
        j = j + 2;
        i++;
        checkKnight(i, j, z, myPosition,board, chessPositions6, chessMoves6, movesCollection);
        i = myPosition.getRow();
        i--;
        checkKnight(i, j, z, myPosition,board, chessPositions6, chessMoves6, movesCollection);
        //decreasing column
        i = myPosition.getRow();
        j = myPosition.getColumn();
        j = j - 2;

        i++;
        checkKnight(i, j, z, myPosition,board, chessPositions6, chessMoves6, movesCollection);

        i = myPosition.getRow();
        i--;
        checkKnight(i, j, z, myPosition,board, chessPositions6, chessMoves6, movesCollection);
        return movesCollection;
    }


    HashSet<ChessMove> checkKnight(int i, int j, int z, ChessPosition myPosition, ChessBoard board, ChessPosition[] chessPositions6, ChessMove[] chessMoves6, HashSet<ChessMove> movesCollection) {
        King king = new King();
        if (j < 9 && j > 0 && i < 9 && i > 0) {
            if (board.getBoardSquares()[i - 1][j - 1] == null) {
                king.place(i, j, z, myPosition, chessPositions6, chessMoves6, movesCollection);
            } else {
                if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                    king.place(i, j, z, myPosition, chessPositions6, chessMoves6, movesCollection);
                }
            }
        }
        return movesCollection;
    }
}
