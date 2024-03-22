package clientTests;

import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;
import server.ResponseException;
import server.Server;
import service.ClearService;
import ui.ServerFacade;


public class ServerFacadeTests {

    private static Server server;
    private ClearService clearService;
    private ServerFacade serverFacade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void sampleTest() {
        Assertions.assertTrue(true);
    }

    @BeforeEach
    public void setUp() throws ResponseException {
        //set up any classes or variables we will need for each test
       clearService.clearAll();

    }

    @Test
    public void registerPass() throws DataAccessException{


    }

    @Test
    public void registerFail() throws DataAccessException{


    }
    @Test
    public void loginPass() throws DataAccessException{


    }

    @Test
    public void loginFail() throws DataAccessException{


    }
    @Test
    public void logoutPass() throws DataAccessException{


    }

    @Test
    public void logoutFail() throws DataAccessException{


    }
    @Test
    public void listGamesPass() throws DataAccessException{


    }

    @Test
    public void listGamesFail() throws DataAccessException{


    }
    @Test
    public void createGamePass() throws DataAccessException{


    }

    @Test
    public void createGameFail() throws DataAccessException{


    }
    @Test
    public void joinGamePass() throws DataAccessException{


    }

    @Test
    public void joinGameFail() throws DataAccessException{


    }



}