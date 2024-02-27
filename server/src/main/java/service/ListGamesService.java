package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import server.requests.ListGamesRequest;
import server.results.ListGamesResult;

public class ListGamesService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public ListGamesService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public ListGamesResult listGames(ListGamesRequest r) {
        //getAuth(authToken)
        //getGames()
        return null;
    }
}
