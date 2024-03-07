package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import server.ResponseException;

public class ClearService {
    //public ClearResult clear() {
        //clear UserDAO
        //clear GameDAO
        //clear AuthDAO
    private final AuthDAO authDAO;
    private final GameDAO gameDAO;
    private final UserDAO userDAO;

    public ClearService (AuthDAO authDAO, GameDAO gameDAO, UserDAO userDAO) {
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
    }
    public void clearAll() {
        try {
            authDAO.clearAuthDatas();
            gameDAO.clearGameDatas();
            userDAO.clearUserDatas();
        }
        catch (ResponseException e) {
            String error = e.getMessage();
        }
    }

}
