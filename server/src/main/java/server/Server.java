package server;

import com.google.gson.Gson;
import dataAccess.*;
import model.UserData;
import server.results.ClearResult;
import server.results.RegisterResult;
import service.ClearService;
import service.RegisterService;
import spark.Request;
import spark.Response;
import spark.Spark;

//does clear not need a request?
//is the way I did the status code alright?
//when does failure 500 get thrown? is it correct for register below?



public class Server {
    AuthDAO authDAO = new MemoryAuthDAO();
    GameDAO gameDAO = new MemoryGameDAO();
    UserDAO userDAO = new MemoryUserDAO();
    private ClearService clearService = new ClearService(authDAO,gameDAO,userDAO);
    public ClearResult clearResult = new ClearResult("",false);

    private RegisterService registerService = new RegisterService(userDAO,authDAO,gameDAO);

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
        Spark.exception(ResponseException.class, this::exceptionHandler);
        //}
        //catch ()
        

        Spark.awaitInitialization();
        return Spark.port();
    }
    //private void exceptionHandler(ResponseException ex, Request req, Response res) {
        //res.status(ex.StatusCode());
    //}
    private void exceptionHandler(ResponseException ex, Request req, Response res) {
        res.status(ex.StatusCode());
    }

    private Object clear(Request req, Response res) {
        //req.headers("authorization");
        clearService.clearAll();
        res.status(200);
        //res.body("[200]");
        return "{}";
    }

    private Object register(Request req, Response res) throws ResponseException{
        try {
            var user = new Gson().fromJson(req.body(), UserData.class);
            res.status(200);
            return new Gson().toJson(registerService.register(user));
        }
        catch (ResponseException e) {
            RegisterResult registerResult = new RegisterResult(e.getMessage());
            if (e.StatusCode() == 403) {
                res.status(403);
            }
            else if (e.StatusCode() == 400) {
                res.status(400);
            }
            //done correctly?
            else {
                res.status(500);
                registerResult.setMessage("Error: description");
            }
            //res.body(e.getMessage());
            return new Gson().toJson(registerResult);
        }

    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}