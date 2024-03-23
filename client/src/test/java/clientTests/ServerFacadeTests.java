package clientTests;

import chess.ChessGame;
import dataAccess.*;
import model.UserData;
import org.junit.jupiter.api.*;
import server.ResponseException;
import server.Server;
import server.requests.CreateGameRequest;
import server.requests.JoinGameRequest;
import server.requests.LoginRequest;
import service.ClearService;
import ui.ServerFacade;

import static org.junit.jupiter.api.Assertions.*;


public class ServerFacadeTests {

    private static Server server;
    AuthDAO authDAO = new AuthSQLDataAccess();
    GameDAO gameDAO = new GameSQLDataAccess();
    UserDAO userDAO = new UserSQLDataAccess();
    private ClearService clearService = new ClearService(authDAO,gameDAO,userDAO);
    private static ServerFacade serverFacade = new ServerFacade("http://localhost:");

    public ServerFacadeTests() throws ResponseException, DataAccessException {
    }

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        serverFacade = new ServerFacade("http://localhost:" + port);
        //need to have a url to put into serverFacade?
        //or need client initiated?
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @BeforeEach
    public void setUp() throws ResponseException {
        //set up any classes or variables we will need for each test
       clearService.clearAll();

    }

    @Test
    public void registerPass() throws ResponseException{
        UserData userData = new UserData("username3","password3","u3@gmail.com");
        var authData = serverFacade.register(userData);
        assertTrue(authData.getAuthToken().length() > 10);

    }

    @Test
    public void registerFail() throws ResponseException {
        UserData userData = new UserData("username3","password3","u3@gmail.com");
        serverFacade.register(userData);
        try {
            serverFacade.register(userData);
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),500);
        }

    }
    @Test
    public void loginPass() throws ResponseException{
        UserData userData = new UserData("username3","password3","u3@gmail.com");
        serverFacade.register(userData);
        LoginRequest loginRequest = new LoginRequest("username3","password3");
        var authData = serverFacade.login(loginRequest);
        assertTrue(authData.getAuthToken().length() > 10);
    }

    @Test
    public void loginFail() throws ResponseException{
        LoginRequest loginRequest = new LoginRequest("username3","password3");
        try {
            var authData = serverFacade.login(loginRequest);
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),500);
        }


    }
    @Test
    public void logoutPass() throws ResponseException{
        UserData userData = new UserData("username3","password3","u3@gmail.com");
        serverFacade.register(userData);
        LoginRequest loginRequest = new LoginRequest("username3","password3");
        var authData = serverFacade.login(loginRequest);
        assertEquals(authDAO.getAuthData(authData.getAuthToken()).getAuthToken(),authData.getAuthToken());
        serverFacade.logout(authData.getAuthToken());
        assertEquals(authDAO.getAuthData(authData.getAuthToken()),null);


    }

    @Test
    public void logoutFail() throws ResponseException{
        try {
            serverFacade.logout("wrong authToken/does not exist");
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),500);
        }

    }
    @Test
    public void listGamesPass() throws ResponseException{

        UserData userData = new UserData("username3","password3","u3@gmail.com");
        serverFacade.register(userData);
        LoginRequest loginRequest = new LoginRequest("username3","password3");
        var authData = serverFacade.login(loginRequest);

        assertEquals(gameDAO.listGameDatas().size(),0);
        CreateGameRequest createGameRequest = new CreateGameRequest("pandaGame",authData.getAuthToken());
        serverFacade.createGame(createGameRequest);
        CreateGameRequest createGameRequest1 = new CreateGameRequest("chocoGame",authData.getAuthToken());
        serverFacade.createGame(createGameRequest1);
        assertEquals(gameDAO.listGameDatas().size(),2);

        var games = serverFacade.listGames(authData.getAuthToken());
        assertNotEquals(games,null);
        assertEquals(games.size(),2);


    }

    @Test
    public void listGamesFail() throws DataAccessException{
        try {
            var games = serverFacade.listGames("authToken that does not exist");
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),500);
        }

    }
    @Test
    public void createGamePass() throws ResponseException{
        UserData userData = new UserData("username3","password3","u3@gmail.com");
        serverFacade.register(userData);
        LoginRequest loginRequest = new LoginRequest("username3","password3");
        var authData = serverFacade.login(loginRequest);
        assertEquals(gameDAO.listGameDatas().size(),0);
        CreateGameRequest createGameRequest = new CreateGameRequest("pandaGame",authData.getAuthToken());
        serverFacade.createGame(createGameRequest);
        assertEquals(gameDAO.listGameDatas().size(),1);

    }

    @Test
    public void createGameFail() throws ResponseException{
        try {
            CreateGameRequest createGameRequest = new CreateGameRequest("pandaGame","authToken that does not exist");
            serverFacade.createGame(createGameRequest);
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),500);
        }

    }
    @Test
    public void joinGamePass() throws ResponseException{
        UserData userData = new UserData("username3","password3","u3@gmail.com");
        serverFacade.register(userData);
        LoginRequest loginRequest = new LoginRequest("username3","password3");
        var authData = serverFacade.login(loginRequest);

        CreateGameRequest createGameRequest = new CreateGameRequest("pandaGame",authData.getAuthToken());
        serverFacade.createGame(createGameRequest);
        CreateGameRequest createGameRequest1 = new CreateGameRequest("chocoGame",authData.getAuthToken());
        var tempGameIDResult = serverFacade.createGame(createGameRequest1);

        JoinGameRequest joinGameRequest = new JoinGameRequest(ChessGame.TeamColor.BLACK,tempGameIDResult.getGameID(),authData.getAuthToken());

        serverFacade.joinGame(joinGameRequest);
        assertEquals(gameDAO.getGameData(tempGameIDResult.getGameID()).getWhiteUsername(),null);
        assertNotEquals(gameDAO.getGameData(tempGameIDResult.getGameID()).getBlackUsername(),null);

    }

    @Test
    public void joinGameFail() throws ResponseException{
        try {
            JoinGameRequest joinGameRequest = new JoinGameRequest(ChessGame.TeamColor.BLACK,7,"authToken not there");

            serverFacade.joinGame(joinGameRequest);

        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),500);
        }

    }



}