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
import server.requests.JoinGameRequest;
import service.CreateGameService;
import service.JoinGameService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoinGameServiceTest {
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

    JoinGameService joinGameService = new JoinGameService(userDAO, authDAO, gameDAO);

    @BeforeEach
    public void setUp() throws DataAccessException ,ResponseException{
        //set up any classes or variables we will need for each test

    }

    @Test
    public void joinGamePass() throws DataAccessException, ResponseException {
        CreateGameService createGameService = new CreateGameService(userDAO,authDAO,gameDAO);
        CreateGameRequest createGameRequest1 = new CreateGameRequest(gameData1.getGameName(),authData1.getAuthToken());
        authDAO.addAuthData(authData1);
        int tempGameID = createGameService.createGame(createGameRequest1).getGameID();


        JoinGameRequest joinGameRequest = new JoinGameRequest(ChessGame.TeamColor.BLACK,tempGameID,authData1.getAuthToken());
        joinGameService.joinGame(joinGameRequest);

        assertEquals(authDAO.getAuthData(authData1.getAuthToken()).getUsername(), gameDAO.getGameData(tempGameID).getBlackUsername());

    }

    @Test
    public void joinGameFail() throws DataAccessException, ResponseException {
        try {
            JoinGameRequest joinGameRequest = new JoinGameRequest(ChessGame.TeamColor.BLACK,246,authData2.getAuthToken());
            joinGameService.joinGame(joinGameRequest);
        }
        catch (ResponseException e) {
            assertEquals(e.StatusCode(),401);
        }


    }
}
