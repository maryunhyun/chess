package server;

import com.google.gson.Gson;
import dataAccess.*;
import model.UserData;
import server.results.ClearResult;
import service.ClearService;
import service.RegisterService;
import spark.Request;
import spark.Response;
import spark.Spark;

//does clear not need a request?



public class Server {
    AuthDAO authDAO = new MemoryAuthDAO();
    GameDAO gameDAO = new MemoryGameDAO();
    UserDAO userDAO = new MemoryUserDAO();
    private ClearService clearService = new ClearService(authDAO,gameDAO,userDAO);
    public ClearResult clearResult = new ClearResult("",false);

    private RegisterService registerService = new RegisterService();

    //private final WebSocketHandler webSocketHandler;


    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        //try {
            Spark.delete("/db", this::clear);
            Spark.post("/user", this::register);
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
        //req.headers("authorization");
        clearService.clearAll();
        res.status(200);
        //res.body("[200]");
        return "{}";
    }

    private Object register(Request req, Response res) {
        var user = new Gson().fromJson(req.body(), UserData.class);
        registerService.register(user);

    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}