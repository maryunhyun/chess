package dataAccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;
import server.ResponseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.sql.Types.NULL;

public class GameSQLDataAccess implements GameDAO{
    public GameSQLDataAccess() throws ResponseException, DataAccessException {
        configureDatabase();
    }

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  game (
              `gameID` INT NOT NULL AUTO_INCREMENT,
              `whiteUsername` varchar(256) DEFAULT NULL,
              `blackUsername` varchar(256) DEFAULT NULL,
              `gameName` varchar(256) NOT NULL,
              `game` longtext NOT NULL,
              PRIMARY KEY (`gameID`)
            );
            """
    };
    private void configureDatabase() throws ResponseException,DataAccessException{
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            for (var statement : createStatements) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new ResponseException(500, String.format("Unable to configure database: %s", ex.getMessage()));
        }
    }

    private int executeUpdate(String statement, Object... params) throws ResponseException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var ps = conn.prepareStatement(statement, RETURN_GENERATED_KEYS)) {
                for (var i = 0; i < params.length; i++) {
                    var param = params[i];
                    if (param instanceof String p) ps.setString(i + 1, p);
                    else if (param instanceof Integer p) ps.setInt(i + 1, p);
                    else if (param == null) ps.setNull(i + 1, NULL);
                }
                ps.executeUpdate();

                var rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }

                return 0;
            }
        } catch (SQLException e) {

            throw new ResponseException(500, String.format("unable to update database: %s, %s", statement, e.getMessage()));

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameData addGameData(GameData gameData) throws ResponseException {
        var statement = "INSERT INTO game (gameID, whiteUsername, blackUsername, gameName, game) VALUES (?, ?, ?, ?, ?)";

        var json = new Gson().toJson(gameData.getGame());

        var test = executeUpdate(statement,gameData.getGameID(),gameData.getWhiteUsername(),gameData.getBlackUsername(),gameData.getGameName(),json);
        return new GameData(gameData.getGameID(),gameData.getWhiteUsername(),gameData.getBlackUsername(),gameData.getGameName(),gameData.getGame());
    }

    @Override
    public Collection<GameData> listGameDatas() {
        var result = new ArrayList<GameData>();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM game";
            try (var ps = conn.prepareStatement(statement)) {
                try (var rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(readGameData(rs));
                    }
                }
            }
        } catch (Exception e) {
            try {
                throw new ResponseException(500, String.format("Unable to read data: %s", e.getMessage()));
            } catch (ResponseException ex) {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }

    @Override
    public GameData getGameData(int gameID) throws ResponseException {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT gameID, whiteUsername, blackUsername, gameName, game FROM game WHERE gameID=?";
            try (var ps = conn.prepareStatement(statement)) {
                ps.setInt(1,gameID);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return readGameData(rs);
                    }
                }
            }
        }
        catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to read data: %s", e.getMessage()));
        }
        return null;
    }

    private GameData readGameData(ResultSet rs) throws SQLException {
        var gameId = rs.getInt("gameID");
        var whiteUsername = rs.getString("whiteUsername");
        var blackUsername = rs.getString("blackUsername");
        var gameName = rs.getString("gameName");

        var json = rs.getString("game");
        var game = new Gson().fromJson(json, ChessGame.class);

        GameData gameData = new GameData(gameId,whiteUsername,blackUsername,gameName,game);
        return gameData;
    }
    @Override
    public void deleteGameData(int gameID) throws ResponseException {
        var statement = "DELETE FROM game WHERE gameID=?";
        executeUpdate(statement, gameID);
    }

    @Override
    public void clearGameDatas() throws ResponseException{
        var statement = "TRUNCATE game";
        executeUpdate(statement);
    }
}
