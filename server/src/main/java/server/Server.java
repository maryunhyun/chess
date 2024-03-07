package server;

import com.google.gson.Gson;
import dataAccess.*;
import model.GameData;
import model.UserData;
import server.requests.CreateGameRequest;
import server.requests.JoinGameRequest;
import server.requests.LoginRequest;
import server.results.*;
import service.*;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.Collection;
import java.util.Map;

//when does failure 500 get thrown? is it correct for register below?
//how to list games? Arraylist?



public class Server {
    AuthDAO authDAO;

    {
        try {
            authDAO = new AuthSQLDataAccess();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    GameDAO gameDAO;

    {
        try {
            gameDAO = new GameSQLDataAccess();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    UserDAO userDAO;

    {
        try {
            userDAO = new UserSQLDataAccess();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private ClearService clearService = new ClearService(authDAO,gameDAO,userDAO);
    public ClearResult clearResult = new ClearResult("");

    private RegisterService registerService = new RegisterService(userDAO,authDAO,gameDAO);
    private LoginService loginService = new LoginService(userDAO,authDAO,gameDAO);
    private LogoutService logoutService = new LogoutService(userDAO,authDAO,gameDAO);
    private CreateGameService createGameService = new CreateGameService(userDAO, authDAO, gameDAO);
    private JoinGameService joinGameService = new JoinGameService(userDAO,authDAO,gameDAO);
    private ListGamesService listGamesService = new ListGamesService(userDAO,authDAO,gameDAO);




    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.


        Spark.delete("/db", this::clear);
        Spark.post("/user", this::register);
        Spark.post("/session", this::login);
        Spark.delete("/session", this::logout);
        Spark.get("/game",this::listGames);
        Spark.post("/game",this::createGame);
        Spark.put("/game",this::joinGame);
        Spark.exception(ResponseException.class, this::exceptionHandler);

        

        Spark.awaitInitialization();
        return Spark.port();
    }

    private void exceptionHandler(ResponseException ex, Request req, Response res) {
        res.status(ex.statusCode());
    }

    private Object clear(Request req, Response res) {
        clearService.clearAll();
        res.status(200);

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
            if (e.statusCode() == 403) {
                res.status(403);
            }
            else if (e.statusCode() == 400) {
                res.status(400);
            }
            //done correctly?
            else {
                res.status(500);
                registerResult.setMessage("Error: description");
            }

            return new Gson().toJson(registerResult);
        }

    }
    private Object login(Request req, Response res) throws ResponseException {
        try {
            var info = new Gson().fromJson(req.body(), LoginRequest.class);
            res.status(200);
            return new Gson().toJson(loginService.login(info));
        }
        catch(ResponseException e) {
            LoginResult loginResult = new LoginResult(e.getMessage());
            if (e.statusCode() == 401) {
                res.status(401);
            }
            //done correctly?
            else {
                res.status(500);
                loginResult.setMessage("Error: description");
            }
            return new Gson().toJson(loginResult);
        }
    }
    private Object logout(Request req, Response res) throws ResponseException {
        try {
            String authToken = req.headers("authorization");
            res.status(200);
            logoutService.logout(authToken);
            return "{}";
        }
        catch(ResponseException e) {
            LogoutResult logoutResult = new LogoutResult(e.getMessage());
            if (e.statusCode() == 401) {
                res.status(401);
            }
            else {
                res.status(500);
                logoutResult.setMessage("Error: description");
            }
            return new Gson().toJson(logoutResult);
        }
    }
    private Object listGames(Request req, Response res) throws ResponseException {
        try {
            String authToken = req.headers("authorization");
            res.status(200);

            var serializer = new Gson();
            Collection<GameData> list = listGamesService.listGames(authToken);

            return new Gson().toJson(Map.of("games", list));

        }
        catch(ResponseException e) {
            ListGamesResult listGamesResult = new ListGamesResult(e.getMessage());
            if (e.statusCode() == 401) {
                res.status(401);
            }
            else {
                res.status(500);
                listGamesResult.setMessage("Error: description");
            }
            return new Gson().toJson(listGamesResult);
        }
    }
    private Object createGame(Request req, Response res) throws ResponseException {
        try {
            var gameName = new Gson().fromJson(req.body(), CreateGameRequest.class);
            String authToken = req.headers("authorization");
            gameName.setAuthToken(authToken);
            res.status(200);
            return new Gson().toJson(createGameService.createGame(gameName));
        }
        catch (ResponseException e) {
            CreateGameMessageResult createGameMessageResult = new CreateGameMessageResult(e.getMessage());
            if (e.statusCode() == 401) {
                res.status(401);
                createGameMessageResult.setMessage("Error: unauthorized");
            }
            else if (e.statusCode() == 400) {
                res.status(400);
                createGameMessageResult.setMessage("Error: bad request");
            }
            else {
                res.status(500);
                createGameMessageResult.setMessage("Error: description");
            }

            return new Gson().toJson(createGameMessageResult);
        }
    }
    private Object joinGame(Request req, Response res) throws ResponseException {
        try {
            var gameInfo = new Gson().fromJson(req.body(), JoinGameRequest.class);
            String authToken = req.headers("authorization");
            gameInfo.setAuthToken(authToken);
            res.status(200);
            joinGameService.joinGame(gameInfo);
            return "{}";
        }
        catch (ResponseException e) {
            JoinGameResult joinGameResult = new JoinGameResult(e.getMessage());
            if (e.statusCode() == 401) {
                res.status(401);
                joinGameResult.setMessage("Error: unauthorized");
            }
            else if (e.statusCode() == 400) {
                res.status(400);
                joinGameResult.setMessage("Error: bad request");
            }
            else if (e.statusCode() == 403) {
                res.status(403);
                joinGameResult.setMessage("Error: already taken");
            }
            else {
                res.status(500);
                joinGameResult.setMessage("Error: description");
            }

            return new Gson().toJson(joinGameResult);
        }
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}