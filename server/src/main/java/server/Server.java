package server;

import spark.Spark;
//what is game in GameData? is it the board/ChessPiece[][]
//is there a late policy like in the syllabus?
//does what I'm doing look right so far?
//requests and responses for each class? And is it alright if they're separate?

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        //for clear
        //Spark.delete("/db",(request, response) -> ClearHandler.getInstance().handleRequest(request,response))??;

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}