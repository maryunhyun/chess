package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.GameData;
import server.ResponseException;

import java.util.Collection;

public class ListGamesService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public ListGamesService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public Collection<GameData> listGames(String authToken) throws ResponseException {
        //getAuth(authToken)
        //getGames()
        AuthData authData = authDAO.getAuthData(authToken);

        if (authDAO.getAuthData(authToken) == null) {
            throw new ResponseException(401,"Error: unauthorized");
        }
        else {

            return gameDAO.listGameDatas();
        }
    }
}
