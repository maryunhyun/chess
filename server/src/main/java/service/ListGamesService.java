package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.GameData;
import server.ResponseException;

public class ListGamesService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public ListGamesService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public GameData[] listGames(String authToken) throws ResponseException {
        //getAuth(authToken)
        //getGames()
        return null;
    }
}
