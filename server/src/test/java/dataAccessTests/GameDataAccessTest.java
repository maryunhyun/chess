package dataAccessTests;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameDataAccessTest {
    private AuthDAO authDAO = new MemoryAuthDAO();
    private UserDAO userDAO = new MemoryUserDAO();

    private GameDAO gameDAO = new MemoryGameDAO();
    private ChessGame chessGame1 = new ChessGame();
    private ChessGame chessGame2 = new ChessGame();
    private AuthData authData1 = new AuthData("123456", "pandazRock");
    private AuthData authData2 = new AuthData("23456", "chocoForever");
    private UserData userData1 = new UserData("pandazRock","password123", "panda@gmail.com");
    private UserData userData2 = new UserData("chocoForever","password234","choco@gmail.com");
    private GameData gameData1 = new GameData(135, "pandazRock", "chocoForever", "pandaChoco", chessGame1);
    private GameData gameData2 = new GameData(3,null,null,"subz",chessGame2);
    CreateGameRequest createGameRequest = new CreateGameRequest("pandaChoco",authData1.getAuthToken());



    ClearService clearService = new ClearService(authDAO, gameDAO, userDAO);
    CreateGameService createGameService = new CreateGameService(userDAO,authDAO,gameDAO);

    @BeforeEach
    public void setUp() throws DataAccessException, ResponseException {
        //set up any classes or variables we will need for each test
        chessGame1.tempBoard.resetBoard();

    }

    @Test
    public void addGameDataPass() throws DataAccessException, ResponseException {
        try {
            gameDAO.getGameData(gameData1.getGameID());
        } catch (ResponseException e) {
            assertEquals(e.statusCode(),401);
        }
        GameData gameData = new GameData(0,null,null,null,null);
        gameData = gameDAO.addGameData(gameData1);
        assertEquals(gameDAO.getGameData(gameData1.getGameID()).getGameID(),gameData.getGameID());
        assertEquals(gameDAO.getGameData(gameData1.getGameID()).getGameName(),gameData.getGameName());

    }

    @Test
    public void addGameDataFail() throws DataAccessException, ResponseException {
        gameDAO.addGameData(gameData1);
        assertNotEquals(gameDAO.listGameDatas().size(),0);
        CreateGameService createGameService1 = new CreateGameService(userDAO,authDAO,gameDAO);


        try {
            createGameService1.createGame(createGameRequest);
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),401);
        }

    }
    @Test
    public void listGameDatasPass() throws DataAccessException, ResponseException {
        clearService.clearAll();
        assertEquals(gameDAO.listGameDatas().size(),0);
        gameDAO.addGameData(gameData1);
        assertEquals(gameDAO.listGameDatas().size(),1);
        gameDAO.addGameData(gameData2);
        assertEquals(true,gameDAO.listGameDatas().contains(gameData1));
        assertEquals(true,gameDAO.listGameDatas().contains(gameData2));
    }

    @Test
    public void listGameDatasFail() throws DataAccessException, ResponseException {
        gameDAO.addGameData(gameData1);
        assertEquals(true,gameDAO.listGameDatas().contains(gameData1));
        gameDAO.addGameData(gameData2);
        clearService.clearAll();
        assertNotEquals(true,gameDAO.listGameDatas().contains(gameData1));
        assertEquals(gameDAO.listGameDatas().size(),0);

    }
    @Test
    public void getGameDataPass() throws DataAccessException, ResponseException {
        clearService.clearAll();
        gameDAO.addGameData(gameData1);
        assertEquals(gameDAO.getGameData(gameData1.getGameID()).getGameID(),gameData1.getGameID());
        assertEquals(gameDAO.getGameData(gameData1.getGameID()).getGameName(),gameData1.getGameName());

    }

    @Test
    public void getGameDataFail() throws DataAccessException, ResponseException {
        clearService.clearAll();
        gameDAO.addGameData(gameData1);
        assertNotEquals(gameDAO.getGameData(gameData1.getGameID()).getGameName(),gameData2.getGameName());
        assertNotEquals(gameDAO.getGameData(gameData1.getGameID()).getGameID(),gameData2.getGameID());

    }
    @Test
    public void deleteGameDataPass() throws DataAccessException, ResponseException {
        clearService.clearAll();
        gameDAO.addGameData(gameData1);
        gameDAO.addGameData(gameData2);
        assertEquals(2,gameDAO.listGameDatas().size());
        gameDAO.deleteGameData(gameData1.getGameID());
        assertEquals(1,gameDAO.listGameDatas().size());
        assertEquals(true,gameDAO.listGameDatas().contains(gameData2));

    }
    @Test
    public void deleteGameDataFail() throws DataAccessException, ResponseException {
        clearService.clearAll();
        gameDAO.addGameData(gameData1);
        gameDAO.addGameData(gameData2);
        assertNotEquals(0,gameDAO.listGameDatas().size());
        gameDAO.deleteGameData(gameData1.getGameID());
        assertNotEquals(2,gameDAO.listGameDatas().size());
        assertEquals(false,gameDAO.listGameDatas().contains(gameData1));
    }


    @Test
    public void clearGameDatasPass() throws DataAccessException, ResponseException {
        gameDAO.addGameData(gameData1);
        gameDAO.addGameData(gameData2);
        clearService.clearAll();

        assertEquals(gameDAO.listGameDatas().size(),0);


    }


}

