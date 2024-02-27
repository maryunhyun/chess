package service;

import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
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
        //getAuth(authToken)
        //getGame(gameID)
        //addPlayer(gameID, username(which username to update determined with ClientColor)
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
                gameDAO.getGameData(r.getGameID()).setWhiteUsername(authDAO.getAuthData(r.getAuthToken()).getUsername());
            }
            else if (r.getPlayerColor() == ChessGame.TeamColor.BLACK) {
                gameDAO.getGameData(r.getGameID()).setBlackUsername(authDAO.getAuthData(r.getAuthToken()).getUsername());
            }
        }
    }
}
