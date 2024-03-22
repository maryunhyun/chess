package ui;

import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import server.ResponseException;
import server.requests.LoginRequest;

import java.util.Arrays;

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
    private final String serverUrl;

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
//                case "join" -> join(params);
//                case "observe" -> observe(params);
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
        throw new ResponseException(400, "Expected: <username> <password>");
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
        throw new ResponseException(400, "Expected: <username> <password> <email>");
    }

//    public String rescuePet(String... params) throws ResponseException {
//        assertSignedIn();
//        if (params.length >= 2) {
//            var name = params[0];
//            var type = PetType.valueOf(params[1].toUpperCase());
//            var pet = new Pet(0, name, type);
//            pet = server.addPet(pet);
//            return String.format("You rescued %s. Assigned ID: %d", pet.name(), pet.id());
//        }
//        throw new ResponseException(400, "Expected: <name> <CAT|DOG|FROG>");
//    }

    public String list() throws ResponseException {
        assertSignedIn();
        int i = 1;
        var games = server.listGames(tempAuthData.getAuthToken());
        var result = new StringBuilder();
        var gson = new Gson();
        for (var game : games) {
            result.append(i);
            result.append(gson.toJson(game)).append('\n');
            i++;
        }
        return result.toString();
    }

    public String create(String... params) throws ResponseException {
        assertSignedIn();
        if (params.length == 1) {
            try {
                var id = Integer.parseInt(params[0]);
                var pet = getPet(id);
                if (pet != null) {
                    server.deletePet(id);
                    return String.format("%s says %s", pet.name(), pet.sound());
                }
            } catch (NumberFormatException ignored) {
            }
        }
        throw new ResponseException(400, "Expected: <pet id>");
    }
//    public String create(String... params) throws ResponseException {
//        assertSignedIn();
//        if (params.length == 1) {
//            try {
//                var id = Integer.parseInt(params[0]);
//                var pet = getPet(id);
//                if (pet != null) {
//                    server.deletePet(id);
//                    return String.format("%s says %s", pet.name(), pet.sound());
//                }
//            } catch (NumberFormatException ignored) {
//            }
//        }
//        throw new ResponseException(400, "Expected: <pet id>");
//    }
//
//    public String adoptAllPets() throws ResponseException {
//        assertSignedIn();
//        var buffer = new StringBuilder();
//        for (var pet : server.listPets()) {
//            buffer.append(String.format("%s says %s%n", pet.name(), pet.sound()));
//        }
//
//        server.deleteAllPets();
//        return buffer.toString();
//    }
//
    public String logout() throws ResponseException {
        assertSignedIn();
        server.logout(tempAuthData.getAuthToken());
        state = State.SIGNEDOUT;
        return String.format("You logged out");
    }
//
//    private Pet getPet(int id) throws ResponseException {
//        for (var pet : server.listPets()) {
//            if (pet.id() == id) {
//                return pet;
//            }
//        }
//        return null;
//    }

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
