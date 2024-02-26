package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;

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
    public AuthData register(UserData userData) {
        //getUser(username)
        //createUser(username, password)
        //createAuth(username)
        AuthData authData = new AuthData(UUID.randomUUID().toString(),userData.getUsername());

        //if (userDAO.getUserData(userData.getUsername()) == null) {
            userDAO.addUserData(userData);
            authDAO.addAuthData(authData);
            return authData;
        //}
        //else {
            //return "User already registered.";
        //}


    }

}
