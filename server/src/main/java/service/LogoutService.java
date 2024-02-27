package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import server.ResponseException;

public class LogoutService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public LogoutService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public void logout(String authToken) throws ResponseException {
        //getAuth(authToken)
        //deleteAuth(authData? or authToken/username)
        if (authDAO.getAuthData(authToken) == null) {
            throw new ResponseException(401,"Error: unauthorized");
        }
        else {
            authDAO.deleteAuthData(authToken);
        }
    }
}
