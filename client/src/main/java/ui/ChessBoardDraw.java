package ui;

import java.io.PrintStream;
import java.util.Random;

import static ui.EscapeSequences.*;

public class ChessBoardDraw {

    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final int SQUARE_SIZE_IN_CHARS = 1;
    private static final int LINE_WIDTH_IN_CHARS = 1;
    private static final String EMPTY = "   ";
    private static final String X = " X ";
    private static final String O = " O ";
    private boolean isOdd = false;
    private static Random rand = new Random();


    public static void drawHeaders(PrintStream out) {

        //setBlack(out);

        String[] headers = { " h ", " g ", " f ", " e ", " d ", " c ", " b ", " a " ,"\n"};

        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
            drawHeader(out, headers[boardCol]);

//            if (boardCol < BOARD_SIZE_IN_SQUARES ) {
//                out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
//            }
        }

        out.println();
    }

    private static void drawHeader(PrintStream out, String headerText) {
        //int prefixLength = SQUARE_SIZE_IN_CHARS / 2;
        //int suffixLength = SQUARE_SIZE_IN_CHARS - prefixLength - 1;

        //out.print(EMPTY.repeat(prefixLength));
        printHeaderText(out, headerText);
        //out.print(EMPTY.repeat(suffixLength));
    }

    private static void printHeaderText(PrintStream out, String columnLabels) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);

        out.print(columnLabels);


        //setBlack(out);
    }
    public void drawChessRowOddHeadRows(PrintStream out) {
        //changes how long the board is vertically/how many rows
        String[] pieces = {" R "," N "," B "," K "," Q "," B "," N "," R "};
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {
                if (((boardCol + 1) % 2) == 1) {
                    isOdd = true;
                }
                else {
                    isOdd = false;
                }
                printPlayer(out, pieces[boardCol]);
            }

        }
    }
    public void drawChessRowEvenHeadRows(PrintStream out) {
        //changes how long the board is vertically/how many rows
        String[] pieces = {" R "," N "," B "," K "," Q "," B "," N "," R "};
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {
                if (((boardCol + 1) % 2) == 1) {
                    isOdd = false;
                }
                else {
                    isOdd = true;
                }
                printPlayer(out, pieces[boardCol]);
            }

        }
    }
    public void drawChessRowEvenHeadRows2(PrintStream out) {
        //changes how long the board is vertically/how many rows
        String[] pieces = {" R "," N "," B "," Q "," K "," B "," N "," R "};
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {
                if (((boardCol + 1) % 2) == 1) {
                    isOdd = false;
                }
                else {
                    isOdd = true;
                }
                printPlayer(out, pieces[boardCol]);
            }

        }
    }
    public void drawChessRowOddHeadRows2(PrintStream out) {
        //changes how long the board is vertically/how many rows
        String[] pieces = {" R "," N "," B "," Q "," K "," B "," N "," R "};
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {
                if (((boardCol + 1) % 2) == 1) {
                    isOdd = true;
                }
                else {
                    isOdd = false;
                }
                printPlayer(out, pieces[boardCol]);
            }

        }
    }
    public void drawChessRowTopLeadRow2(PrintStream out) {
        //changes how long the board is vertically/how many rows
        String[] pieces = { " a ", " b ", " c ", " d ", " e ", " f ", " g ", " h "};
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {

                out.print(SET_BG_COLOR_BLACK);
                out.print(SET_TEXT_COLOR_CREME_YELLOW);
                out.print(pieces[boardCol]);
            }

        }
    }
    public void drawChessRowBottomLeadRows2(PrintStream out) {
        //changes how long the board is vertically/how many rows
        String[] pieces = { " a ", " b ", " c ", " d ", " e ", " f ", " g ", " h "};
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {

                out.print(SET_BG_COLOR_BLACK);
                out.print(SET_TEXT_COLOR_CREME_YELLOW);
                out.print(pieces[boardCol]);
            }

        }
    }
    public void drawChessRowTopLeadRow(PrintStream out) {
        //changes how long the board is vertically/how many rows
        String[] pieces = { " h ", " g ", " f ", " e ", " d ", " c ", " b ", " a "};
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {

                out.print(SET_BG_COLOR_BLACK);
                out.print(SET_TEXT_COLOR_CREME_YELLOW);
                out.print(pieces[boardCol]);
            }

        }
    }

    public void drawChessRowBottomLeadRows(PrintStream out) {
        //changes how long the board is vertically/how many rows
        String[] pieces = { " h ", " g ", " f ", " e ", " d ", " c ", " b ", " a "};
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {

                out.print(SET_BG_COLOR_BLACK);
                out.print(SET_TEXT_COLOR_CREME_YELLOW);
                out.print(pieces[boardCol]);
            }

        }
    }

    public void drawChessRowOddRows(PrintStream out) {
        //changes how long the board is vertically/how many rows
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {
                if (((boardCol + 1) % 2) == 1) {
                    isOdd = true;
                }
                else {
                    isOdd = false;
                }
                printPlayer(out, "   ");
            }

        }
    }
    public void drawFillerRows(PrintStream out) {
        //changes how long the board is vertically/how many rows
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES + 2; boardCol++) {
                isOdd = true;
                printPlayer(out, "   ");
            }

        }
    }
    public void drawChessRowOddSecondHeaderRows(PrintStream out) {
        //changes how long the board is vertically/how many rows
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {
                if (((boardCol + 1) % 2) == 1) {
                    isOdd = true;
                }
                else {
                    isOdd = false;
                }
                printPlayer(out, " P ");
            }

        }
    }
    public void drawChessRowEvenSecondHeaderRows(PrintStream out) {
        //changes how long the board is vertically/how many rows
        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {
                if (((boardCol + 1) % 2) == 1) {
                    isOdd = false;
                }
                else {
                    isOdd = true;
                }
                printPlayer(out, " P ");
            }

        }
    }
    public void drawChessRowEvenRows(PrintStream out) {
        //changes how long the board is vertically/how many rows

        for (int boardRow = 0; boardRow < 1; ++boardRow) {
            //drawRowOfSquares(out,boardRow, boardCol);
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; boardCol++) {
                if (((boardCol + 1) % 2) == 1) {
                    isOdd = false;
                }
                else {
                    isOdd = true;
                }
                printPlayer(out, "   ");
            }

        }
    }
    public void drawChessBoard1(PrintStream out) {
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print("   ");
            drawChessRowTopLeadRow(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print("   ");
            out.println();
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 1 ");
            drawChessRowOddHeadRows(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 1 ");
            out.println();
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 2 ");
            drawChessRowEvenSecondHeaderRows(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 2 ");
            out.println();
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 3 ");
            drawChessRowOddRows(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 3 ");
            out.println();
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 4 ");
            drawChessRowEvenRows(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 4 ");
            out.println();
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 5 ");
            drawChessRowOddRows(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 5 ");
            out.println();
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 6 ");
            drawChessRowEvenRows(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 6 ");
            out.println();
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 7 ");
            drawChessRowOddSecondHeaderRows(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 7 ");
            out.println();
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 8 ");
            drawChessRowEvenHeadRows(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print(" 8 ");
            out.println();
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print("   ");
            drawChessRowBottomLeadRows(out);
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_CREME_YELLOW);
            out.print("   ");
            out.println();
            drawFillerRows(out);
            out.println();

    }
    public void drawChessBoard2(PrintStream out) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print("   ");
        drawChessRowTopLeadRow2(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print("   ");
        out.println();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 8 ");
        drawChessRowOddHeadRows2(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 8 ");
        out.println();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 7 ");
        drawChessRowEvenSecondHeaderRows(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 7 ");
        out.println();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 6 ");
        drawChessRowOddRows(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 6 ");
        out.println();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 5 ");
        drawChessRowEvenRows(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 5 ");
        out.println();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 4 ");
        drawChessRowOddRows(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 4 ");
        out.println();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 3 ");
        drawChessRowEvenRows(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 3 ");
        out.println();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 2 ");
        drawChessRowOddSecondHeaderRows(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 2 ");
        out.println();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 1 ");
        drawChessRowEvenHeadRows2(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print(" 1 ");
        out.println();
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print("   ");
        drawChessRowBottomLeadRows2(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);
        out.print("   ");
        out.println();

    }

    private void drawRowOfSquares(PrintStream out, int boardRow, int boardCol) {
        //changes distance between rows
        for (int squareRow = 0; squareRow < SQUARE_SIZE_IN_CHARS; ++squareRow) {
            //changes how wide the board is or how many columns
            while(boardCol < BOARD_SIZE_IN_SQUARES) {
                //changed colors between rows
                //setWhite(out);


                if (squareRow == SQUARE_SIZE_IN_CHARS / 2) {
                    int prefixLength = SQUARE_SIZE_IN_CHARS / 2;
                    int suffixLength = SQUARE_SIZE_IN_CHARS - prefixLength - 1;

                    out.print(EMPTY.repeat(prefixLength));
                    //printPlayer(out, rand.nextBoolean() ? X : O);

                    out.print(EMPTY.repeat(suffixLength));


                }
                else {
//                    out.print(EMPTY.repeat(SQUARE_SIZE_IN_CHARS));
//                    if ((boardRow % 2) == 0) {
//                        isOdd = true;
//                        if ((boardCol % 2) == 1) {
//                            isOdd = false;
//                        }
//                    }
//                    else {
//                        isOdd = false;
//                        if ((boardCol % 2) == 1) {
//                            isOdd = true;
//                        }
//                    }
                }

//                if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
//                    // Draw right line
//                    setRed(out);
//                    out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
//                }
                boardCol++;
                out.print(SET_BG_COLOR_DARK_GREY);
            }

            out.println();
        }
    }

    private static void setWhite(PrintStream out) {
        out.print(SET_BG_COLOR_WHITE);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private void printPlayer(PrintStream out, String player) {
        if (isOdd) {
            out.print(SET_BG_COLOR_CREME_BROWN);
        }
        else {
            out.print(SET_BG_COLOR_CREME_YELLOW);
        }
        out.print(SET_TEXT_COLOR_BLACK);

        out.print(player);

        //setWhite(out);
    }
}

