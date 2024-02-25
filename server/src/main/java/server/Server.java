package server;

import spark.*;
//what is game in GameData? is it the board/ChessPiece[][]
//is there a late policy like in the syllabus?

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        //for clear
        Spark.delete("/db","put handler function here???");

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}