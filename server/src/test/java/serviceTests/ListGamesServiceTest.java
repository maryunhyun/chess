package serviceTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ResponseException;
import server.requests.CreateGameRequest;
import service.ClearService;
import service.CreateGameService;
import service.ListGamesService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListGamesServiceTest {
    private AuthDAO authDAO = new MemoryAuthDAO();
    private UserDAO userDAO = new MemoryUserDAO();

    private GameDAO gameDAO = new MemoryGameDAO();
    private ChessGame chessGame1 = new ChessGame();
    private ChessGame chessGame2 = new ChessGame();
    private AuthData authData1 = new AuthData("123456", "pandazRock");
    private AuthData authData2 = new AuthData("23456", "chocoForever");
    private UserData userData1 = new UserData("pandazRock", "password123", "panda@gmail.com");
    private UserData userData2 = new UserData("chocoForever", "password234", "choco@gmail.com");
    private GameData gameData1 = new GameData(135, "pandazRock", "chocoForever", "pandaChoco", chessGame1);
    private GameData gameData2 = new GameData(246, "pandazRWierd", "chocoGross", "wierdGross", chessGame2);

    ListGamesService listGamesService = new ListGamesService(userDAO, authDAO, gameDAO);

    @BeforeEach
    public void setUp() throws DataAccessException ,ResponseException{
        //set up any classes or variables we will need for each test
        CreateGameService createGameService = new CreateGameService(userDAO,authDAO,gameDAO);
        CreateGameRequest createGameRequest1 = new CreateGameRequest(gameData1.getGameName(),authData1.getAuthToken());
        CreateGameRequest createGameRequest2 = new CreateGameRequest(gameData2.getGameName(),authData1.getAuthToken());
        authDAO.addAuthData(authData1);

        createGameService.createGame(createGameRequest1);
        createGameService.createGame(createGameRequest2);

    }

    @Test
    public void listGamesPass() throws DataAccessException, ResponseException {

        assertEquals(gameDAO.listGameDatas().size(), 2);
        assertEquals(listGamesService.listGames(authData1.getAuthToken()).size(), 2);

        ClearService clearService = new ClearService(authDAO,gameDAO,userDAO);
        clearService.clearAll();
        try {
            listGamesService.listGames(authData1.getAuthToken()).size();
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),401);
        }

    }

    @Test
    public void listGamesFail() throws DataAccessException, ResponseException {
        try{
            listGamesService.listGames(authData2.getAuthToken());
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),401);
        }


    }
}
