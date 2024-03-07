package dataAccessTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ResponseException;
import server.requests.CreateGameRequest;
import server.requests.LoginRequest;
import service.ClearService;
import service.CreateGameService;
import service.LoginService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserDataAccessTest {
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
        chessGame1.tempBoard.resetBoard();

    }

    @Test
    public void addUserDataPass() throws DataAccessException, ResponseException {
        try {
            authDAO.getAuthData(authData1.getAuthToken());
        } catch (ResponseException e) {
            assertEquals(e.statusCode(),401);
        }
        AuthData authData = new AuthData(null,null);
        authData = authDAO.addAuthData(authData1);
        assertEquals(authDAO.getAuthData(authData1.getAuthToken()).getAuthToken(),authData.getAuthToken());
        assertEquals(authDAO.getAuthData(authData1.getAuthToken()).getUsername(),authData.getUsername());

    }

    @Test
    public void addUserDataFail() throws DataAccessException, ResponseException {
        authDAO.addAuthData(authData1);
        assertNotEquals(authDAO.listAuthDatas().size(),0);
        LoginService loginService = new LoginService(userDAO,authDAO,gameDAO);
        LoginRequest loginRequest = new LoginRequest(null,userData1.getPassword());


        try {
            loginService.login(loginRequest);
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),401);
        }

    }
    @Test
    public void listUserDatasPass() throws DataAccessException, ResponseException {
        clearService.clearAll();
        assertEquals(authDAO.listAuthDatas().size(),0);
        authDAO.addAuthData(authData1);
        assertEquals(authDAO.listAuthDatas().size(),1);
        authDAO.addAuthData(authData2);
        assertEquals(true,authDAO.listAuthDatas().contains(authData1));
        assertEquals(true,authDAO.listAuthDatas().contains(authData2));
    }

    @Test
    public void listUserDatasFail() throws DataAccessException, ResponseException {
        authDAO.addAuthData(authData1);
        assertEquals(true,authDAO.listAuthDatas().contains(authData1));
        authDAO.addAuthData(authData2);
        clearService.clearAll();
        assertNotEquals(true,authDAO.listAuthDatas().contains(authData1));
        assertEquals(authDAO.listAuthDatas().size(),0);

    }
    @Test
    public void getUserDataPass() throws DataAccessException, ResponseException {
        clearService.clearAll();
        authDAO.addAuthData(authData1);
        assertEquals(authDAO.getAuthData(authData1.getAuthToken()).getAuthToken(),authData1.getAuthToken());
        assertEquals(authDAO.getAuthData(authData1.getAuthToken()).getUsername(),authData1.getUsername());

    }

    @Test
    public void getUserDataFail() throws DataAccessException, ResponseException {
        clearService.clearAll();
        authDAO.addAuthData(authData1);
        assertNotEquals(authDAO.getAuthData(authData1.getAuthToken()).getAuthToken(),authData2.getAuthToken());
        assertNotEquals(authDAO.getAuthData(authData1.getAuthToken()).getUsername(),authData2.getUsername());

    }



    @Test
    public void clearUserDatasPass() throws DataAccessException, ResponseException {
        authDAO.addAuthData(authData1);
        authDAO.addAuthData(authData2);
        clearService.clearAll();

        assertEquals(authDAO.listAuthDatas().size(),0);


    }


}

