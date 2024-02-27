package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import server.requests.JoinGameRequest;
import server.results.JoinGameResult;

public class JoinGameService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public JoinGameService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public JoinGameResult joinGame(JoinGameRequest r) {
        //getAuth(authToken)
        //getGame(gameID)
        //addPlayer(gameID, username(which username to update determined with ClientColor)
        return null;
    }
}
