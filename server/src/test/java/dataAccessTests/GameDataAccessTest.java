package dataAccessTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
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
    private AuthData authData1 = new AuthData("123456", "pandazRock");
    private AuthData authData2 = new AuthData("23456", "chocoForever");
    private UserData userData1 = new UserData("pandazRock","password123", "panda@gmail.com");
    private UserData userData2 = new UserData("chocoForever","password234","choco@gmail.com");
    CreateGameRequest createGameRequest = new CreateGameRequest("pandaChoco",authData1.getAuthToken());



    ClearService clearService = new ClearService(authDAO, gameDAO, userDAO);
    CreateGameService createGameService = new CreateGameService(userDAO,authDAO,gameDAO);

    @BeforeEach
    public void setUp() throws DataAccessException, ResponseException {
        //set up any classes or variables we will need for each test
        authDAO.addAuthData(authData1);
        authDAO.addAuthData(authData2);

        userDAO.addUserData(userData1);
        userDAO.addUserData(userData2);

        int tempGameID = createGameService.createGame(createGameRequest).getGameID();
        gameDAO.getGameData(tempGameID).setBlackUsername("pandazRock");
        gameDAO.getGameData(tempGameID).setWhiteUsername("chocoForever");

    }

    @Test
    public void addGameDataPass() throws DataAccessException{
        clearService.clearAll();

        assertEquals(authDAO.listAuthDatas().size(),0);
        assertEquals(gameDAO.listGameDatas().size(),0);
        assertEquals(userDAO.listUserDatas().size(),0);

    }

    @Test
    public void addGameDataFail() throws DataAccessException{

        assertNotEquals(authDAO.listAuthDatas().size(),0);
        assertNotEquals(gameDAO.listGameDatas().size(),0);
        assertNotEquals(userDAO.listUserDatas().size(),0);

    }
    @Test
    public void listGameDatasPass() throws DataAccessException{
        clearService.clearAll();

        assertEquals(authDAO.listAuthDatas().size(),0);
        assertEquals(gameDAO.listGameDatas().size(),0);
        assertEquals(userDAO.listUserDatas().size(),0);

    }

    @Test
    public void listGameDatasFail() throws DataAccessException{

        assertNotEquals(authDAO.listAuthDatas().size(),0);
        assertNotEquals(gameDAO.listGameDatas().size(),0);
        assertNotEquals(userDAO.listUserDatas().size(),0);

    }
    @Test
    public void getGameDataPass() throws DataAccessException{
        clearService.clearAll();

        assertEquals(authDAO.listAuthDatas().size(),0);
        assertEquals(gameDAO.listGameDatas().size(),0);
        assertEquals(userDAO.listUserDatas().size(),0);

    }

    @Test
    public void getGameDataFail() throws DataAccessException{

        assertNotEquals(authDAO.listAuthDatas().size(),0);
        assertNotEquals(gameDAO.listGameDatas().size(),0);
        assertNotEquals(userDAO.listUserDatas().size(),0);

    }
    @Test
    public void deleteGameDataPass() throws DataAccessException{
        clearService.clearAll();

        assertEquals(authDAO.listAuthDatas().size(),0);
        assertEquals(gameDAO.listGameDatas().size(),0);
        assertEquals(userDAO.listUserDatas().size(),0);

    }

    @Test
    public void clearGameDatasPass() throws DataAccessException{
        clearService.clearAll();

        assertEquals(authDAO.listAuthDatas().size(),0);
        assertEquals(gameDAO.listGameDatas().size(),0);
        assertEquals(userDAO.listUserDatas().size(),0);

    }


}

