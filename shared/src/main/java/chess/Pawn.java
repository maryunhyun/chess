package chess;

import java.util.HashSet;

public class Pawn {
    public HashSet<ChessMove> pawnMoves(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> movesCollection) {
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
                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.BISHOP);
                        movesCollection.add(chessMoves7[z]);
                        z++;
                        chessPositions7[z] = new ChessPosition(i, j);
                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.KNIGHT);
                        movesCollection.add(chessMoves7[z]);
                        z++;
                        chessPositions7[z] = new ChessPosition(i, j);
                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.QUEEN);
                        movesCollection.add(chessMoves7[z]);
                        z++;
                        chessPositions7[z] = new ChessPosition(i, j);
                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.ROOK);
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
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.BISHOP);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.KNIGHT);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.QUEEN);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.ROOK);
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
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.BISHOP);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.KNIGHT);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.QUEEN);
                            movesCollection.add(chessMoves7[z]);
                            z++;
                            chessPositions7[z] = new ChessPosition(i, j);
                            chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.ROOK);
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
                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.BISHOP);
                        movesCollection.add(chessMoves7[z]);
                        z++;
                        chessPositions7[z] = new ChessPosition(i, j);
                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.KNIGHT);
                        movesCollection.add(chessMoves7[z]);
                        z++;
                        chessPositions7[z] = new ChessPosition(i, j);
                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.QUEEN);
                        movesCollection.add(chessMoves7[z]);
                        z++;
                        chessPositions7[z] = new ChessPosition(i, j);
                        chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.ROOK);
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
                if (j < 9) {
                    if (board.getBoardSquares()[i - 1][j - 1] != null) {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            //promotion if reach end of board
                            if (i == 8) {
                                //get chosen promotion option and insert
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.BISHOP);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.KNIGHT);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.QUEEN);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.ROOK);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                            } else {
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                            }
                        }
                    }
                }
                j = j-2;
                if (j > 0) {
                    if (board.getBoardSquares()[i - 1][j - 1] != null) {
                        if (board.getBoardSquares()[i - 1][j - 1].getTeamColor() != board.getBoardSquares()[myPosition.getRow() - 1][myPosition.getColumn() - 1].getTeamColor()) {
                            //promotion if reach end of board
                            if (i == 8) {
                                //get chosen promotion option and insert
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.BISHOP);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.KNIGHT);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.QUEEN);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], ChessPiece.PieceType.ROOK);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                            } else {
                                chessPositions7[z] = new ChessPosition(i, j);
                                chessMoves7[z] = new ChessMove(myPosition, chessPositions7[z], null);
                                movesCollection.add(chessMoves7[z]);
                                z++;
                            }
                        }
                    }
                }
                i = myPosition.getRow();
                j = myPosition.getColumn();
            }
        }
        return movesCollection;
    }
}
