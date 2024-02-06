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
    public ChessBoard tempBoard = new ChessBoard();

    public InvalidMoveException invalidMoveException = new InvalidMoveException();
    //public ChessPiece[][] tempSquares = new ChessPiece[8][8];
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
        return this.chessBoard.squares[startPosition.getRow()-1][startPosition.getColumn()-1].pieceMoves(this.chessBoard,startPosition);
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

        if (chessBoard.squares[move.chessStartPosition.chessRow-1][move.chessStartPosition.chessCol-1] != null) {
            if (chessBoard.squares[move.chessStartPosition.chessRow - 1][move.chessStartPosition.chessCol - 1].chessPieceColor != getTeamTurn()) {
                throw invalidMoveException;
            }
            else if (move.chessEndPosition.chessRow > 8 | move.chessEndPosition.chessRow < 1 | move.chessEndPosition.chessCol > 8 | move.chessEndPosition.chessCol < 1) {
                throw invalidMoveException;
            }
            boolean moveValid = false;
            HashSet<ChessMove> validTempMoves = new HashSet<>();
            validTempMoves.addAll(validMoves(move.chessStartPosition));

            for (ChessMove chessMove : validTempMoves) {
                if (move.chessEndPosition.chessCol == chessMove.chessEndPosition.chessCol && move.chessEndPosition.chessRow == chessMove.chessEndPosition.chessRow) {
                    moveValid = true;
                }
            }
            if (!moveValid) {
                throw new InvalidMoveException();
            }
        }

        //deleting piece in old spot (start position) and putting it into new spot (end position)
        ChessPiece tempPiece = new ChessPiece(null,null);

        tempPiece.setChessPieceColor(chessBoard.squares[move.chessStartPosition.getRow()-1][move.chessStartPosition.getColumn()-1].getTeamColor());
        tempPiece.setChessType(chessBoard.squares[move.chessStartPosition.getRow()-1][move.chessStartPosition.getColumn()-1].getPieceType());


        //what if scenario of deleting piece in old spot (start position) and putting it into new spot (end position)
        boolean danger = false;
        copyBoard(chessBoard);

        ChessPiece tempPiece1 = new ChessPiece(null,null);
        tempPiece1.setChessPieceColor(tempBoard.squares[move.chessStartPosition.getRow()-1][move.chessStartPosition.getColumn()-1].getTeamColor());
        tempPiece1.setChessType(tempBoard.squares[move.chessStartPosition.getRow()-1][move.chessStartPosition.getColumn()-1].getPieceType());


        tempBoard.squares[move.chessStartPosition.getRow()-1][move.chessStartPosition.getColumn()-1] = null;

        tempBoard.squares[move.chessEndPosition.getRow()-1][move.chessEndPosition.getColumn()-1] = tempPiece1;

        if (tempIsInCheck(getTeamTurn(),tempBoard)) {
            danger = true;
        }
        if (danger) {
            throw invalidMoveException;
        }


        //check for promotion and promote
        if (move.chessPromotionPiece != null) {
            tempPiece.setChessType(move.chessPromotionPiece);
        }

        chessBoard.squares[move.chessStartPosition.getRow()-1][move.chessStartPosition.getColumn()-1] = null;

        chessBoard.squares[move.chessEndPosition.getRow()-1][move.chessEndPosition.getColumn()-1] = tempPiece;
        //now other player's turn
        if (turnTeamColor == TeamColor.BLACK) {
            setTeamTurn(TeamColor.WHITE);
        }
        else {
            setTeamTurn(TeamColor.BLACK);
        }




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
                        movesCollectionCheck.addAll(chessBoard.squares[i][j].pieceMoves(this.chessBoard, tempPiecePosition));
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
        HashSet<ChessPosition> pawnPossiblePlaces = new HashSet<>();
        HashSet<ChessPosition> closePawnPossiblePlaces = new HashSet<>();
        ChessPosition tempKingPosition = new ChessPosition(0,0);
        ChessPosition [] tempPiecePositions = new ChessPosition[70];
        ChessPosition [] pawnPositions = new ChessPosition[70];
        HashSet<ChessMove> movesCollectionCheck = new HashSet<>();
        //ChessMove tempMove1 = new ChessMove(null, null, null);
        //ChessMove tempMove2 = new ChessMove(null, null, null);
        int z = 0;
        int d = 0;

        //find the correct king's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.chessBoard.squares[i][j] != null) {
                    if (this.chessBoard.squares[i][j].chessType == ChessPiece.PieceType.KING && this.chessBoard.squares[i][j].chessPieceColor == teamColor) {
                        tempKingPosition.setChessRow(i + 1);
                        tempKingPosition.setChessCol(j + 1);
                        kingPossibleMoves.addAll(this.chessBoard.squares[i][j].pieceMoves(this.chessBoard, tempKingPosition));
                    }
                    else {
                        tempPiecePositions[d] = new ChessPosition(i + 1,j + 1);
                        if (this.chessBoard.squares[i][j].chessType == ChessPiece.PieceType.PAWN && this.chessBoard.squares[i][j].chessPieceColor != teamColor) {
                            pawnPositions[z] = new ChessPosition(i +1, j + 1);
                            pawnPossiblePlaces.add(pawnPositions[z]);
                            z++;
                        }

                        movesCollectionCheck.addAll(chessBoard.squares[i][j].pieceMoves(this.chessBoard, tempPiecePositions[d]));
                        d++;
                    }
                }
            }
        }
        boolean spaceCanBeTaken = false;

        // if (!isInCheck(teamColor)) {
        //checkMate = false;
        // }
        // else {

        //check if pawn close by and add to new set of close pawns
        for (ChessPosition chessPosition : pawnPossiblePlaces) {
            if (chessPosition.chessRow < tempKingPosition.chessRow + 3 && chessPosition.chessRow > tempKingPosition.chessRow - 3 && chessPosition.chessCol < tempKingPosition.chessCol + 3 && chessPosition.chessCol > tempKingPosition.chessCol - 3) {
                closePawnPossiblePlaces.add(chessPosition);
            }
        }

        for (ChessMove chessMoveKing : kingPossibleMoves) {
            spaceCanBeTaken = false;
            for (ChessPosition chessPosition : closePawnPossiblePlaces) {
                if (chessMoveKing.chessEndPosition.chessRow + 1 == chessPosition.chessRow && chessMoveKing.chessEndPosition.chessCol + 1 == chessPosition.chessCol) {
                    spaceCanBeTaken = true;
                }
                else if (chessMoveKing.chessEndPosition.chessRow + 1 == chessPosition.chessRow && chessMoveKing.chessEndPosition.chessCol - 1 == chessPosition.chessCol) {
                    spaceCanBeTaken = true;
                }
                else if (chessMoveKing.chessEndPosition.chessRow - 1 == chessPosition.chessRow && chessMoveKing.chessEndPosition.chessCol - 1 == chessPosition.chessCol) {
                    spaceCanBeTaken = true;
                }
                else if (chessMoveKing.chessEndPosition.chessRow - 1 == chessPosition.chessRow && chessMoveKing.chessEndPosition.chessCol + 1 == chessPosition.chessCol) {
                    spaceCanBeTaken = true;
                }
            }
            for (ChessMove chessMoveEnemy : movesCollectionCheck) {
                if (chessMoveEnemy.chessEndPosition.chessRow == chessMoveKing.chessEndPosition.chessRow && chessMoveEnemy.chessEndPosition.chessCol == chessMoveKing.chessEndPosition.chessCol) {
                    spaceCanBeTaken = true;
                    break;
                }
                if (chessBoard.squares[chessMoveKing.chessEndPosition.chessRow-1][chessMoveKing.chessEndPosition.chessCol-1] != null) {
                    copyBoard(chessBoard);

                    //deleting piece in old spot (start position) and putting it into new spot (end position)
                    ChessPiece tempPiece = new ChessPiece(null,null);
                    tempPiece.setChessPieceColor(tempBoard.squares[chessMoveKing.chessStartPosition.getRow()-1][chessMoveKing.chessStartPosition.getColumn()-1].getTeamColor());
                    tempPiece.setChessType(tempBoard.squares[chessMoveKing.chessStartPosition.getRow()-1][chessMoveKing.chessStartPosition.getColumn()-1].getPieceType());


                    tempBoard.squares[chessMoveKing.chessStartPosition.getRow()-1][chessMoveKing.chessStartPosition.getColumn()-1] = null;

                    tempBoard.squares[chessMoveKing.chessEndPosition.getRow()-1][chessMoveKing.chessEndPosition.getColumn()-1] = tempPiece;

                    if (tempIsInCheck(teamColor,tempBoard)) {
                        spaceCanBeTaken = true;
                    }
                }
                //check if move of king is a move someone else could make if the king killed a person and opened that space to an enemy cokming to get him
                //if king's move is != null (killed someone from enemy team)...
                //make new temporary board that copies old board and moves king (make funtion copy board? - for loop through old board and add into new one)
                //then see all possible moves of pieces with new setup
                //check if king's move matches with an enemy's
                //else if ()
            }
            if (!spaceCanBeTaken) {
                checkMate = false;
            }
        }
        //}
        boolean danger = false;
        if (kingPossibleMoves.size() == 0) {
            for (ChessMove chessMove : movesCollectionCheck) {
                if (chessMove.chessEndPosition.chessCol == tempKingPosition.chessCol && chessMove.chessEndPosition.chessRow == tempKingPosition.chessRow) {
                    danger = true;
                }
            }
            if (!danger) {
                checkMate = false;
            }
        }



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
        //add up set of valid moves with function valid moves and only add moves with teamColor
        //if not valid moves, stalemate is true

        boolean stalemate = false;
        ChessPosition tempPiecePosition = new ChessPosition(0,0);
        HashSet<ChessMove> movesCollectionCheck = new HashSet<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.chessBoard.squares[i][j] != null && this.chessBoard.squares[i][j].chessPieceColor == teamColor && this.chessBoard.squares[i][j].chessType != ChessPiece.PieceType.KING) {
                    tempPiecePosition.setChessRow(i+1);
                    tempPiecePosition.setChessCol(j+1);

                    movesCollectionCheck.addAll(chessBoard.squares[i][j].pieceMoves(chessBoard, tempPiecePosition));

                }
            }
        }
        if (movesCollectionCheck.size() == 0 && isInCheckmate(teamColor)) {
            stalemate = true;
        }
        return stalemate;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.chessBoard = board;
    }

    public void copyBoard(ChessBoard board) {
        //tempBoard.resetBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.squares[i][j] != null) {
                    tempBoard.squares[i][j] = new ChessPiece(board.squares[i][j].chessPieceColor, board.squares[i][j].chessType);
                }
            }
        }
    }

    public boolean tempIsInCheck(TeamColor teamColor, ChessBoard tempBoard) {
        boolean check = false;
        //go through squares and find king of the correct team color to get his position
        //go through opposing team's moves and see if his position is one of them
        ChessPosition tempKingPosition = new ChessPosition(0,0);
        ChessPosition tempPiecePosition = new ChessPosition(0,0);
        HashSet<ChessMove> movesCollectionCheck = new HashSet<>();

        //find the correct king's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tempBoard.squares[i][j] != null) {
                    if (tempBoard.squares[i][j].chessType == ChessPiece.PieceType.KING && tempBoard.squares[i][j].chessPieceColor == teamColor) {
                        tempKingPosition.setChessRow(i + 1);
                        tempKingPosition.setChessCol(j + 1);
                    }
                    else {
                        tempPiecePosition.setChessRow(i+1);
                        tempPiecePosition.setChessCol(j+1);
                        /*for (int z = 0; z < chessBoard.squares[i][j].pieceMoves(this.chessBoard,tempPiecePosition).size(); z++) {
                            movesCollectionCheck.add(chessBoard.squares[i][j].pieceMoves(this.chessBoard,tempPiecePosition)[z]);
                        }*/
                        movesCollectionCheck.addAll(tempBoard.squares[i][j].pieceMoves(this.tempBoard, tempPiecePosition));
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
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return chessBoard;
    }
}
