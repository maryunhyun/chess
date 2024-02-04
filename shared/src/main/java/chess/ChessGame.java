package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    public TeamColor turnTeamColor = TeamColor.WHITE;
    public ChessBoard chessBoard;
    public ChessGame() {

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return turnTeamColor;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.turnTeamColor = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public HashSet<ChessMove> validMoves(ChessPosition startPosition) {
        return this.chessBoard.squares[startPosition.getRow()][startPosition.getColumn()].pieceMoves(this.chessBoard,startPosition);
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     * move is illegal if the chess piece cannot move there,
     * if the move leaves the team’s king in danger, or if it’s not the corresponding team's turn.
     *
     * change piece's end position to make a move
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        //deleting piece in old spot (start position) and putting it into new spot (end position)
        ChessPiece tempPiece = new ChessPiece(null,null);
        tempPiece.setChessPieceColor(chessBoard.squares[move.chessStartPosition.getRow()-1][move.chessStartPosition.getColumn()-1].getTeamColor());
        tempPiece.setChessType(chessBoard.squares[move.chessStartPosition.getRow()-1][move.chessStartPosition.getColumn()-1].getPieceType());


        chessBoard.squares[move.chessStartPosition.getRow()-1][move.chessStartPosition.getColumn()-1] = null;

        chessBoard.squares[move.chessEndPosition.getRow()-1][move.chessEndPosition.getColumn()-1] = tempPiece;


    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        boolean check = false;
        //go through squares and find king of the correct team color to get his position
        //go through opposing team's moves and see if his position is one of them
        ChessPosition tempKingPosition = new ChessPosition(0,0);
        ChessPosition tempPiecePosition = new ChessPosition(0,0);
        HashSet<ChessMove> movesCollectionCheck = new HashSet<>();

        //find the correct king's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.chessBoard.squares[i][j] != null) {
                    if (this.chessBoard.squares[i][j].chessType == ChessPiece.PieceType.KING && this.chessBoard.squares[i][j].chessPieceColor == teamColor) {
                        tempKingPosition.setChessRow(i + 1);
                        tempKingPosition.setChessCol(j + 1);
                    }
                    else {
                        tempPiecePosition.setChessRow(i+1);
                        tempPiecePosition.setChessCol(j+1);
                        /*for (int z = 0; z < chessBoard.squares[i][j].pieceMoves(this.chessBoard,tempPiecePosition).size(); z++) {
                            movesCollectionCheck.add(chessBoard.squares[i][j].pieceMoves(this.chessBoard,tempPiecePosition)[z]);
                        }*/
                        for (ChessMove chessMove : chessBoard.squares[i][j].pieceMoves(this.chessBoard,tempPiecePosition)) {
                            movesCollectionCheck.add(chessMove);
                        }
                    }
                }
            }
        }

        //see if king's position is a possible move for a piece
        for (ChessMove chessMove : movesCollectionCheck) {
            if (chessMove.chessEndPosition.chessRow == tempKingPosition.chessRow && chessMove.chessEndPosition.chessCol == tempKingPosition.chessCol) {
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        //all of king's possible moves are isInCheck (true for function isInCheck)
        boolean checkMate = true;
        HashSet<ChessMove> kingPossibleMoves = new HashSet<>();
        ChessPosition tempKingPosition = new ChessPosition(0,0);
        ChessPosition tempPiecePosition = new ChessPosition(0,0);
        HashSet<ChessMove> movesCollectionCheck = new HashSet<>();
        ChessMove tempMove = new ChessMove(null, null, null);

        //find the correct king's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.chessBoard.squares[i][j] != null) {
                    if (this.chessBoard.squares[i][j].chessType == ChessPiece.PieceType.KING && this.chessBoard.squares[i][j].chessPieceColor == teamColor) {
                        tempKingPosition.setChessRow(i + 1);
                        tempKingPosition.setChessCol(j + 1);
                        for (ChessMove chessMove : this.chessBoard.squares[i][j].pieceMoves(this.chessBoard,tempKingPosition)) {
                            kingPossibleMoves.add(chessMove);
                        }
                    }
                    else {
                        tempPiecePosition.setChessRow(i+1);
                        tempPiecePosition.setChessCol(j+1);
                        if (this.chessBoard.squares[i][j].chessType == ChessPiece.PieceType.PAWN) {
                            //tempMove
                            movesCollectionCheck.add(tempMove);
                        }
                        /*for (int z = 0; z < chessBoard.squares[i][j].pieceMoves(this.chessBoard,tempPiecePosition).size(); z++) {
                            movesCollectionCheck.add(chessBoard.squares[i][j].pieceMoves(this.chessBoard,tempPiecePosition)[z]);
                        }*/
                        for (ChessMove chessMove : chessBoard.squares[i][j].pieceMoves(this.chessBoard,tempPiecePosition)) {
                            movesCollectionCheck.add(chessMove);
                        }
                    }
                }
            }
        }
        boolean spaceCanBeTaken = false;

        // if (!isInCheck(teamColor)) {
        //checkMate = false;
        // }
        // else {
        for (ChessMove chessMoveKing : kingPossibleMoves) {
            spaceCanBeTaken = false;
            for (ChessMove chessMoveEnemy : movesCollectionCheck) {
                if (chessMoveEnemy.chessEndPosition.chessRow == chessMoveKing.chessEndPosition.chessRow && chessMoveEnemy.chessEndPosition.chessCol == chessMoveKing.chessEndPosition.chessCol) {
                    spaceCanBeTaken = true;
                }
            }
            if (!spaceCanBeTaken) {
                checkMate = false;
            }
        }
        //}


        return checkMate;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.chessBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return chessBoard;
    }
}
