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
import service.CreateGameService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateGameServiceTest {
    private AuthDAO authDAO = new MemoryAuthDAO();
    private UserDAO userDAO = new MemoryUserDAO();

    private GameDAO gameDAO = new MemoryGameDAO();
    private ChessGame chessGame1 = new ChessGame();
    private AuthData authData1 = new AuthData("123456", "pandazRock");
    private AuthData authData2 = new AuthData("23456", "chocoForever");
    private UserData userData1 = new UserData("pandazRock", "password123", "panda@gmail.com");
    private UserData userData2 = new UserData("chocoForever", "password234", "choco@gmail.com");
    private GameData gameData1 = new GameData(135, "pandazRock", "chocoForever", "pandaChoco", chessGame1);

    CreateGameService createGameService = new CreateGameService(userDAO, authDAO, gameDAO);

    @BeforeEach
    public void setUp() throws DataAccessException {
        //set up any classes or variables we will need for each test

    }

    @Test
    public void createGamePass() throws DataAccessException, ResponseException {
        authDAO.addAuthData(authData1);
        CreateGameRequest createGameRequest = new CreateGameRequest(gameData1.getGameName(),authData1.getAuthToken());

        createGameService.createGame(createGameRequest);

        assertEquals(gameDAO.listGameDatas().size(), 1);

    }

    @Test
    public void createGameFail() throws DataAccessException, ResponseException {
        try {
            CreateGameRequest createGameRequest = new CreateGameRequest(gameData1.getGameName(),authData1.getAuthToken());

            createGameService.createGame(createGameRequest);
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),401);
        }

    }
}