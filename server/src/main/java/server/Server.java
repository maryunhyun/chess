package server;

import spark.Spark;
//what is game in GameData? is it the board/ChessPiece[][]
//does what I'm doing look right so far?
//requests and responses for each class? And is it alright if they're separate?
//what exactly is the MemoryUserDAO?
//in the catch in the Server, is this where I handle all of the failure responses and output stuff? Or is this in the results?
//do I handle headers here or in the requests?
//what is the difference between the body and headers?

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        //try {
            //Spark.delete("/db",(request, response) -> ClearHandler.handleRequest(request,response))??;
            //Spark.post("/user", ((request, response) -> RegisterHandler.handleRequest(request,response)));
            //Spark.post("/session", ((request, response) -> LoginHandler.handleRequest(request,response)));
            //Spark.delete("/session", ((request, response) -> LogoutHandler.handleRequest(request,response)));
            //Spark.get("/game",((request, response) -> ListGamesHandler.handleRequest(request,response)));
            //Spark.post("/game",((request, response) -> CreateGameHandler.handleRequest(request,response)));
            //Spark.put("/game",((request, response) -> JoinGameHandler.handleRequest(request,response)));
        //}
        //catch ()

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}