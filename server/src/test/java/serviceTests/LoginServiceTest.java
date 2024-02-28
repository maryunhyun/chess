package serviceTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ResponseException;
import server.requests.LoginRequest;
import service.LoginService;
import service.RegisterService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoginServiceTest {
    private AuthDAO authDAO = new MemoryAuthDAO();
    private UserDAO userDAO = new MemoryUserDAO();

    private GameDAO gameDAO = new MemoryGameDAO();
    private ChessGame chessGame1 = new ChessGame();
    private AuthData authData1 = new AuthData("123456", "pandazRock");
    private AuthData authData2 = new AuthData("23456", "chocoForever");
    private UserData userData1 = new UserData("pandazRock", "password123", "panda@gmail.com");
    private UserData userData2 = new UserData("chocoForever", "password234", "choco@gmail.com");
    private GameData gameData1 = new GameData(135, "pandazRock", "chocoForever", "pandaChoco", chessGame1);

    LoginService loginService = new LoginService(userDAO, authDAO, gameDAO);

    @BeforeEach
    public void setUp() throws DataAccessException {
        //set up any classes or variables we will need for each test


    }

    @Test
    public void loginPass() throws DataAccessException, ResponseException {
        RegisterService registerService = new RegisterService(userDAO,authDAO,gameDAO);
        registerService.register(userData1);
        LoginRequest loginRequest1 = new LoginRequest(userData1.getUsername(),userData1.getPassword());

        AuthData authData1 = new AuthData(null,null);
        authData1.setAuthToken(loginService.login(loginRequest1).getAuthToken());

        assertNotEquals(authData1.getAuthToken(), null);

    }

    @Test
    public void loginFail() throws DataAccessException, ResponseException {
        try {
            LoginRequest loginRequest2 = new LoginRequest(userData2.getUsername(),userData2.getPassword());
            loginService.login(loginRequest2);
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),401);
        }


    }
}
