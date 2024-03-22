package ui;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static ui.EscapeSequences.*;

public class Repl {
    private final Client client;
    private ChessBoardDraw chessBoardDraw = new ChessBoardDraw();

    public Repl(String serverUrl) {
        client = new Client(serverUrl);
    }

    public void run() {
        //output board here?
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.println("♕ Welcome to 240 Chess. Type Help to get started. ♕");
        System.out.print("[");
        if (client.getState() == State.SIGNEDIN) {
            System.out.print("LOGGED_IN] >>> ");
        }
        else {
            System.out.print("LOGGED_OUT] >>> ");
        }

        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit") && !result.equals("Goodbye!")) {
            String line = scanner.nextLine();

            try {
                result = client.eval(line);
                if (client.drawBoard) {
                    var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
                    out.print(ERASE_SCREEN);
                    //chessBoardDraw.drawHeaders(out);
                    chessBoardDraw.drawTicTacToeBoard(out);
                    //chessBoardDraw.drawHeaders(out);

                }
                //System.out.print(SET_BG_COLOR_MATCH_SCREEN);
                System.out.print(SET_TEXT_COLOR_BLUE + result);
                System.out.println();
                //find the '-' and then output in PURPLE??

                System.out.print(SET_TEXT_COLOR_WHITE);
                if (!result.equals("quit") && !result.equals("Goodbye!")) {
                    System.out.print("[");
                    if (client.getState() == State.SIGNEDIN) {
                        System.out.print("LOGGED_IN] >>> ");
                    } else {
                        System.out.print("LOGGED_OUT] >>> ");
                    }
                }
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(msg);
            }
        }
        System.out.println();
    }


}
