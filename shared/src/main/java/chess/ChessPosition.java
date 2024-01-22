package chess;

import java.util.Objects;

/**
 * Represents a single square position on a chess board
 *
 * This represents a location on the chessboard. This
 * should be represented as a row number from 1-8, and a
 * column number from 1-8. For example, (1,1) corresponds to
 * the bottom left corner (which in chess notation is denoted a1).
 * (8,8) corresponds to the top right corner (h8 in chess notation).
 *
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {
    public int chessRow;
    public int chessCol;
    public ChessPosition(int row, int col) {
        this.chessRow = row;
        this.chessCol = col;
    }


    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return chessRow;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return chessCol;
    }

    public void setChessRow(int tempRow) {
        this.chessRow = tempRow;
    }

    public void setChessCol(int tempCol) {
        this.chessCol = tempCol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that = (ChessPosition) o;
        return chessRow == that.chessRow && chessCol == that.chessCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chessRow, chessCol);
    }

    @Override
    public String toString() {
        return "ChessPosition{" +
                "chessRow=" + chessRow +
                ", chessCol=" + chessCol +
                '}';
    }
}
