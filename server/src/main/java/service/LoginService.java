package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import server.ResponseException;
import server.requests.LoginRequest;

import java.util.UUID;

public class LoginService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public LoginService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public AuthData login(LoginRequest loginRequest) throws ResponseException {
        AuthData tempAuthData = new AuthData(UUID.randomUUID().toString(),loginRequest.getUsername());


        if (userDAO.getUserData(loginRequest.getUsername()) == null) {
            throw new ResponseException(401,"Error: unauthorized");
        }
        else if (!(userDAO.getUserData(loginRequest.getUsername()).getPassword().equals(loginRequest.getPassword()))) {
            throw new ResponseException(401,"Error: unauthorized");
        }
        else {
            authDAO.addAuthData(tempAuthData);
        }

        return tempAuthData;
    }
}
