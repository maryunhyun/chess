package service;

import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.GameData;
import server.ResponseException;
import server.requests.CreateGameRequest;
import server.results.CreateGameIDResult;

public class CreateGameService {
    UserDAO userDAO;
    AuthDAO authDAO;
    GameDAO gameDAO;
    public int nextID = 1;
    public CreateGameService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
    }
    public CreateGameIDResult createGame(CreateGameRequest r) throws ResponseException {
        ChessGame chessGame = new ChessGame();
        GameData gameData = new GameData(nextID++, null, null, r.getGameName(), chessGame);
        if (r.getGameName() == null | r.getAuthToken() == null) {
            throw new ResponseException(400, "Error: bad request");
        }
        else if (authDAO.getAuthData(r.getAuthToken()) == null) {
            throw new ResponseException(401, "Error: unauthorized");
        }
        else {
            gameData = gameDAO.addGameData(gameData);
            CreateGameIDResult createGameIDResult = new CreateGameIDResult(gameData.getGameID());
            return createGameIDResult;
        }


    }
}
