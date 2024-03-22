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

        String[] headers = { " h ", " g ", " f ", " e ", " d ", " c ", " b ", " a " };
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

    private static void printHeaderText(PrintStream out, String player) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_CREME_YELLOW);

        out.print(player);

        //setBlack(out);
    }

    public void drawTicTacToeBoard(PrintStream out) {

        for (int boardRow = 0; boardRow < BOARD_SIZE_IN_SQUARES; ++boardRow) {

            drawRowOfSquares(out);

            if (boardRow < BOARD_SIZE_IN_SQUARES - 1) {
                //drawVerticalLine(out);

            }
        }
    }

    private void drawRowOfSquares(PrintStream out) {

        for (int squareRow = 0; squareRow < SQUARE_SIZE_IN_CHARS; ++squareRow) {
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
                setWhite(out);


                if (squareRow == SQUARE_SIZE_IN_CHARS / 2) {
                    int prefixLength = SQUARE_SIZE_IN_CHARS / 2;
                    int suffixLength = SQUARE_SIZE_IN_CHARS - prefixLength - 1;

                    out.print(EMPTY.repeat(prefixLength));
                    printPlayer(out, rand.nextBoolean() ? X : O);
                    out.print(EMPTY.repeat(suffixLength));
                    if ((squareRow % 2) == 0) {
                        isOdd = true;
                        if ((boardCol % 2) == 1) {
                            isOdd = false;
                        }
                    }
                    else {
                        isOdd = true;
                        if ((boardCol % 2) == 1) {
                            isOdd = false;
                        }
                    }
                }
                else {
                    out.print(EMPTY.repeat(SQUARE_SIZE_IN_CHARS));
                    if ((squareRow % 2) == 0) {
                        isOdd = true;
                        if ((boardCol % 2) == 1) {
                            isOdd = false;
                        }
                    }
                    else {
                        isOdd = false;
                        if ((boardCol % 2) == 1) {
                            isOdd = true;
                        }
                    }
                }

//                if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
//                    // Draw right line
//                    setRed(out);
//                    out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
//                }

                out.print(SET_BG_COLOR_DARK_GREY);
            }
            out.println();
        }
    }

    private static void drawVerticalLine(PrintStream out) {

        int boardSizeInSpaces = BOARD_SIZE_IN_SQUARES * SQUARE_SIZE_IN_CHARS +
                (BOARD_SIZE_IN_SQUARES - 1) * LINE_WIDTH_IN_CHARS;

        for (int lineRow = 0; lineRow < LINE_WIDTH_IN_CHARS; ++lineRow) {
            setRed(out);
            out.print(EMPTY.repeat(boardSizeInSpaces));

            setBlack(out);
            out.println();
        }
    }

    private static void setWhite(PrintStream out) {
        out.print(SET_BG_COLOR_WHITE);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void setRed(PrintStream out) {
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_RED);
    }

    private static void setBlack(PrintStream out) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_BLACK);
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

        setWhite(out);
    }
}

