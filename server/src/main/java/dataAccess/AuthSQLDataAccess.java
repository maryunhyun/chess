package dataAccess;

import com.google.gson.Gson;
import model.AuthData;
import server.ResponseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.sql.Types.NULL;
//help with SQL app and how to see if what I'm doing is right
//does my table set up look correct?
//maybe lay out how you want the rest of the to look and ask if right?

public class AuthSQLDataAccess implements AuthDAO{
    public AuthSQLDataAccess() throws ResponseException, DataAccessException {
        configureDatabase();
    }

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  auth (
              `authToken` varchar(256) NOT NULL,
              `username` varchar(256) NOT NULL,
              `json` TEXT DEFAULT NULL,
              PRIMARY KEY (`authToken`),
              INDEX(username)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
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

    private int executeUpdate(String statement, Object... params) {
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
            try {
                throw new ResponseException(500, String.format("unable to update database: %s, %s", statement, e.getMessage()));
            } catch (ResponseException ex) {
                throw new RuntimeException(ex);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthData addAuthData(AuthData authData) {
        var statement = "INSERT INTO auth (username, json) VALUES (?, ?)";
        var json = new Gson().toJson(authData);
        var authToken = executeUpdate(statement,authData.getUsername(),json);
        return new AuthData(Integer.toString(authToken),authData.getUsername());
    }

    @Override
    public Collection<AuthData> listAuthDatas() {
        var result = new ArrayList<AuthData>();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT authToken, json FROM auth";
            try (var ps = conn.prepareStatement(statement)) {
                try (var rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(readAuthData(rs));
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
    public AuthData getAuthData(String authToken) {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT authToken, json FROM auth WHERE authToken=?";
            try (var ps = conn.prepareStatement(statement)) {
                ps.setString(1,authToken);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return readAuthData(rs);
                    }
                }
            }
        }
        catch (Exception e) {
            try {
                throw new ResponseException(500, String.format("Unable to read data: %s", e.getMessage()));
            } catch (ResponseException ex) {
                throw new RuntimeException(ex);
            }
        }
        return null;
    }

    private AuthData readAuthData(ResultSet rs) throws SQLException {
        var authToken = rs.getString("authToken");
        var json = rs.getString("json");
        var authData = new Gson().fromJson(json, AuthData.class);
        authData.setAuthToken(authToken);
        return new AuthData(authData.getAuthToken(),authData.getUsername());
    }

    @Override
    public void deleteAuthData(String authToken) {
        var statement = "DELETE FROM auth WHERE authToken=?";
        executeUpdate(statement, authToken);
    }

    @Override
    public void clearAuthDatas() {
        var statement = "TRUNCATE auth";
        executeUpdate(statement);
    }
}
