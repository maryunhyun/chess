package service;

import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.GameData;
import server.ResponseException;
import server.requests.JoinGameRequest;

public class JoinGameService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public JoinGameService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public void joinGame(JoinGameRequest r) throws ResponseException {
        boolean taken = false;


        if (authDAO.getAuthData(r.getAuthToken()) == null) {
            throw new ResponseException(401,"Error: unauthorized");
        }
        else if (gameDAO.getGameData(r.getGameID()) == null) {
            throw new ResponseException(400,"Error: bad request");
        }

        if (r.getPlayerColor() == ChessGame.TeamColor.WHITE) {
            if (gameDAO.getGameData(r.getGameID()).getWhiteUsername() != null) {
                taken = true;
            }
        }
        else if (r.getPlayerColor() == ChessGame.TeamColor.BLACK) {
            if (gameDAO.getGameData(r.getGameID()).getBlackUsername() != null) {
                taken = true;
            }
        }

        if (taken) {
            throw new ResponseException(403,"Error: already taken");
        }
        else {
            if (r.getPlayerColor() == ChessGame.TeamColor.WHITE) {
                String tempAuthToken = r.getAuthToken();
                AuthData authData3 = authDAO.getAuthData(tempAuthToken);
                String tempUsername = authData3.getUsername();
                GameData gameData2 = gameDAO.getGameData(r.getGameID());
                gameData2.setWhiteUsername(tempUsername);
                //remove old game to insert new one with new username;
                gameDAO.deleteGameData(gameData2.getGameID());
                gameDAO.addGameData(gameData2);
                //gameDAO.getGameData(r.getGameID()).setWhiteUsername(authDAO.getAuthData(r.getAuthToken()).getUsername());
            }
            else if (r.getPlayerColor() == ChessGame.TeamColor.BLACK) {
                String tempAuthToken = r.getAuthToken();
                AuthData authData3 = authDAO.getAuthData(tempAuthToken);
                String tempUsername = authData3.getUsername();
                GameData gameData2 = gameDAO.getGameData(r.getGameID());
                gameData2.setBlackUsername(tempUsername);
                //remove old game to insert new one with new username;
                gameDAO.deleteGameData(gameData2.getGameID());
                gameDAO.addGameData(gameData2);
                //gameDAO.getGameData(r.getGameID()).setBlackUsername(authDAO.getAuthData(r.getAuthToken()).getUsername());
            }
        }
    }
}
