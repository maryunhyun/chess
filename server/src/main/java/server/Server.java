package server;

import service.ClearService;
import spark.Request;
import spark.Response;
import spark.Spark;
//what is game in GameData? is it the board/ChessPiece[][]
//does what I'm doing look right so far?
//requests and responses for each class? And is it alright if they're separate?
//what exactly is the MemoryUserDAO?
//in the catch in the Server, is this where I handle all of the failure responses and output stuff? Or is this in the results?
//do I handle headers here or in the requests?
//what is the difference between the body and headers?
//are exceptions supposed to be handled like down below?
//for dataaccess/DAOs, do you need to make ResponseException?
//MemoryDAO correct?
//do I make the random authToken with UUID in memoryauthDAO?

public class Server {
    private final ClearService clearService = new ClearService();
    //private final WebSocketHandler webSocketHandler;


    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        //try {
            Spark.delete("/db",this::clear);
            //Spark.post("/user", ((request, response) -> RegisterHandler.handleRequest(request,response)));
            //Spark.post("/session", ((request, response) -> LoginHandler.handleRequest(request,response)));
            //Spark.delete("/session", ((request, response) -> LogoutHandler.handleRequest(request,response)));
            //Spark.get("/game",((request, response) -> ListGamesHandler.handleRequest(request,response)));
            //Spark.post("/game",((request, response) -> CreateGameHandler.handleRequest(request,response)));
            //Spark.put("/game",((request, response) -> JoinGameHandler.handleRequest(request,response)));
        //Spark.exception(ResponseException.class, this::exceptionHandler);
        //}
        //catch ()

        Spark.awaitInitialization();
        return Spark.port();
    }
    //private void exceptionHandler(ResponseException ex, Request req, Response res) {
        //res.status(ex.StatusCode());
    //}

    private Object clear(Request req, Response res) {
        clearService.clear();
        res.status(200);
        return "";
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}