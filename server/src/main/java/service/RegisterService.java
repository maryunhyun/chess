package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;
import server.ResponseException;

import java.util.UUID;

public class RegisterService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public RegisterService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public AuthData register(UserData userData) throws ResponseException {

        AuthData authData = new AuthData(UUID.randomUUID().toString(),userData.getUsername());

        if (userData.getUsername() == null | userData.getEmail() == null | userData.getPassword() == null) {
            throw new ResponseException(400, "Error: bad request");
        }
        else if (userDAO.getUserData(userData.getUsername()) != null) {
            throw new ResponseException(403, "Error: already taken");
        }
        else if (userDAO.getUserData(userData.getUsername()) == null & userData.getUsername() != null & userData.getEmail() != null & userData.getPassword() != null){
            userDAO.addUserData(userData);
            authDAO.addAuthData(authData);
            return authData;
        }
        else {
            throw new ResponseException(500, "Error: description");
        }



    }

}
