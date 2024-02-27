package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import server.requests.LogoutRequest;
import server.results.LogoutResult;

public class LogoutService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public LogoutService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public LogoutResult logout(LogoutRequest r) {
        //getAuth(authToken)
        //deleteAuth(authData? or authToken/username)
        return null;
    }
}
