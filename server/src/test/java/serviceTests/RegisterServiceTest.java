package serviceTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ResponseException;
import service.RegisterService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterServiceTest {
    private AuthDAO authDAO = new MemoryAuthDAO();
    private UserDAO userDAO = new MemoryUserDAO();

    private GameDAO gameDAO = new MemoryGameDAO();
    private ChessGame chessGame1 = new ChessGame();
    private AuthData authData1 = new AuthData("123456", "pandazRock");
    private AuthData authData2 = new AuthData("23456", "chocoForever");
    private UserData userData1 = new UserData("pandazRock","password123", "panda@gmail.com");
    private UserData userData2 = new UserData("chocoForever","password234","choco@gmail.com");
    private GameData gameData1 = new GameData(135,"pandazRock","chocoForever","pandaChoco",chessGame1);

    RegisterService registerService = new RegisterService(userDAO,authDAO,gameDAO);

    @BeforeEach
    public void setUp() throws DataAccessException {
        //set up any classes or variables we will need for each test


    }

    @Test
    public void registerPass() throws DataAccessException, ResponseException {
        registerService.register(userData1);

        assertEquals(authDAO.listAuthDatas().size(),1);
        assertEquals(userDAO.listUserDatas().size(),1);

    }

    @Test
    public void registerFail() throws DataAccessException, ResponseException{
        try {
            registerService.register(userData1);
        }
        catch (ResponseException e) {
            assertEquals(e.statusCode(),403);
        }


    }
}
