package ui;

import java.util.Scanner;

public class Repl {
    private final Client client;

    public Repl(String serverUrl) {
        client = new Client(serverUrl);
    }

    public void run() {
        //output board here?
        System.out.println("\uD83D\uDC36 Welcome to the pet store. Sign in to start.");
        System.out.print(client.help());

        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit")) {
            String line = scanner.nextLine();

            try {
                result = client.eval(line);
                //System.out.print(BLUE + result);
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(msg);
            }
        }
        System.out.println();
    }


}
