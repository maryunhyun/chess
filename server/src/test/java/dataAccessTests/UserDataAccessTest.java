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
import service.RegisterService;

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
            userDAO.getUserData(userData1.getUsername());
        } catch (ResponseException e) {
            assertEquals(e.statusCode(),401);
        }
        UserData userData = new UserData(null,null,null);
        userData = userDAO.addUserData(userData1);
        assertEquals(userDAO.getUserData(userData1.getUsername()).getUsername(),userData.getUsername());
        assertEquals(userDAO.getUserData(userData1.getUsername()).getPassword(),userData.getPassword());

    }

    @Test
    public void addUserDataFail() throws DataAccessException, ResponseException {
        userDAO.addUserData(userData1);
        assertNotEquals(userDAO.listUserDatas().size(),0);
        RegisterService registerService = new RegisterService(userDAO,authDAO,gameDAO);
        UserData userData = new UserData(null, userData1.getPassword(),userData1.getEmail());

        try {
            registerService.register(userData);
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),400);
        }

    }
    @Test
    public void listUserDatasPass() throws DataAccessException, ResponseException {
        clearService.clearAll();
        assertEquals(userDAO.listUserDatas().size(),0);
        userDAO.addUserData(userData1);
        assertEquals(userDAO.listUserDatas().size(),1);
        userDAO.addUserData(userData2);
        assertEquals(true,userDAO.listUserDatas().contains(userData1));
        assertEquals(true,userDAO.listUserDatas().contains(userData2));
    }

    @Test
    public void listUserDatasFail() throws DataAccessException, ResponseException {
        userDAO.addUserData(userData1);
        assertEquals(true,userDAO.listUserDatas().contains(userData1));
        userDAO.addUserData(userData2);
        clearService.clearAll();
        assertNotEquals(true,userDAO.listUserDatas().contains(userData1));
        assertEquals(userDAO.listUserDatas().size(),0);

    }
    @Test
    public void getUserDataPass() throws DataAccessException, ResponseException {
        clearService.clearAll();
        userDAO.addUserData(userData1);
        assertEquals(userDAO.getUserData(userData1.getUsername()).getUsername(),userData1.getUsername());
        assertEquals(userDAO.getUserData(userData1.getUsername()).getEmail(),userData1.getEmail());

    }

    @Test
    public void getUserDataFail() throws DataAccessException, ResponseException {
        clearService.clearAll();
        userDAO.addUserData(userData1);
        assertNotEquals(userDAO.getUserData(userData1.getUsername()).getUsername(),userData2.getUsername());
        assertNotEquals(userDAO.getUserData(userData1.getUsername()).getPassword(),userData2.getPassword());

    }



    @Test
    public void clearUserDatasPass() throws DataAccessException, ResponseException {
        userDAO.addUserData(userData1);
        userDAO.addUserData(userData2);
        clearService.clearAll();

        assertEquals(userDAO.listUserDatas().size(),0);


    }


}

