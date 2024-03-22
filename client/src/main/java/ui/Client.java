package ui;

import chess.ChessGame;
import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import server.ResponseException;
import server.requests.CreateGameRequest;
import server.requests.JoinGameRequest;
import server.requests.LoginRequest;

import java.util.Arrays;
import java.util.HashMap;

public class Client {
    //prelogin
    //help, quit, login, register
    //postlogin
    //for all of these, need to start with assertsignedin
    //help, logout, create game, list games, join game, join observer

    //need to be able to get authToken for authorization for some like list games
    //correct or not how I did it with tempAuthData?
    private String userName = null;
    private final ServerFacade server;
    private AuthData tempAuthData = new AuthData(null,null);
    private int tempGameIDResult = 0;
    private final String serverUrl;
    HashMap<Integer, Integer> gameListNumberAndID = new HashMap<>();

    private State state = State.SIGNEDOUT;

    public Client(String serverUrl) {
        server = new ServerFacade(serverUrl);
        this.serverUrl = serverUrl;
    }

    public String eval(String input) {
        try {
            var tokens = input.toLowerCase().split(" ");
            var cmd = (tokens.length > 0) ? tokens[0] : "help";
            var params = Arrays.copyOfRange(tokens, 1, tokens.length);
            return switch (cmd) {
                case "register" -> register(params);
                case "login" -> login(params);
                case "list" -> list();
                case "logout" -> logout();
                case "create" -> create(params);
                case "join" -> join(params);
                case "observe" -> observe(params);
                case "quit" -> "Goodbye!";
                case "help" -> help();
                default -> "";
            };
        } catch (ResponseException ex) {
            return ex.getMessage();
        }
    }

    public String login(String... params) throws ResponseException {
        if (params.length == 2) {
            AuthData tempLoginAuthData = new AuthData(null,null);
            userName = String.join("-", params);

            LoginRequest loginRequest = new LoginRequest(params[0],params[1]);
            tempLoginAuthData = server.login(loginRequest);

            tempAuthData.setUsername(tempLoginAuthData.getUsername());
            tempAuthData.setAuthToken(tempLoginAuthData.getAuthToken());
            state = State.SIGNEDIN;

            return String.format("You logged in as %s.", userName);
        }
        throw new ResponseException(400, "Expected: <USERNAME> <PASSWORD>");
    }

    public String register(String... params) throws ResponseException {
        if (params.length == 3) {
            AuthData tempRegisterAuthData = new AuthData(null,null);
            userName = String.join("-", params);

            UserData userData = new UserData(params[0],params[1],params[2]);
            tempRegisterAuthData = server.register(userData);

            tempAuthData.setAuthToken(tempRegisterAuthData.getAuthToken());
            tempAuthData.setUsername(tempRegisterAuthData.getUsername());

            state = State.SIGNEDIN;

            return String.format("You registered as %s.", userName);
        }
        throw new ResponseException(400, "Expected: <USERNAME> <PASSWORD> <EMAIL>");
    }


    public String list() throws ResponseException {
        assertSignedIn();
        int i = 1;
        var games = server.listGames(tempAuthData.getAuthToken());
        if (games == null) {
            return "no games available, please create a game to observe or play";
        }
        var result = new StringBuilder();
        var gson = new Gson();
        for (var game : games) {
            result.append(i);
            result.append(gson.toJson(game)).append('\n');
            gameListNumberAndID.put(i,game.getGameID());
            i++;
        }
        return result.toString();
    }

    public String create(String... params) throws ResponseException {
        assertSignedIn();
        if (params.length == 1) {
            CreateGameRequest createGameRequest = new CreateGameRequest(params[0],tempAuthData.getAuthToken());
            tempGameIDResult = server.createGame(createGameRequest).getGameID();
        }
        throw new ResponseException(400, "Expected: <NAME>");
    }

    public String join(String... params) throws ResponseException {
        assertSignedIn();
        if (params.length == 2 | params.length == 1) {
            int gameID = gameListNumberAndID.get(params[0]);
            ChessGame.TeamColor teamColor = null;
            if (params.length == 2) {
                if (params[1] == "WHITE" | params[1] == "white") {
                    teamColor = ChessGame.TeamColor.WHITE;
                }
                else if (params[1] == "BLACK" | params[1] == "black") {
                    teamColor = ChessGame.TeamColor.BLACK;
                }
            }
            JoinGameRequest joinGameRequest = new JoinGameRequest(teamColor,gameID,tempAuthData.getAuthToken());
            server.joinGame(joinGameRequest);
        }
        throw new ResponseException(400, "Expected: <ID> [WHITE|BLACK|<empty>]");
    }

    public String observe(String... params) throws ResponseException {
        assertSignedIn();
        if ( params.length == 1) {
            int gameID = gameListNumberAndID.get(params[0]);

            JoinGameRequest joinGameRequest = new JoinGameRequest(null,gameID,tempAuthData.getAuthToken());
            server.joinGame(joinGameRequest);
        }
        throw new ResponseException(400, "Expected: <ID> [WHITE|BLACK|<empty>]");
    }

    public String logout() throws ResponseException {
        assertSignedIn();
        server.logout(tempAuthData.getAuthToken());
        state = State.SIGNEDOUT;
        return String.format("You logged out");
    }

    public String help() {
        if (state == State.SIGNEDOUT) {
            return """
                    register <USERNAME> <PASSWORD> <EMAIL> - to create an account
                    login <USERNAME> <PASSWORD> - to play chess
                    quit - playing chess
                    help - with possible commands
                    """;
        }
        return """
                create <NAME> - a game
                list - games
                join <ID> [WHITE|BLACK|<empty>] - a game
                observe <ID> - a game
                logout - when you are done
                quit - playing chess
                help - with possible commands
                """;
    }

    private void assertSignedIn() throws ResponseException {
        if (state == State.SIGNEDOUT) {
            throw new ResponseException(400, "You must sign in");
        }
    }

    public State getState() {
        return state;
    }
}
