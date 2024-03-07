package dataAccess;

import model.UserData;
import server.ResponseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.sql.Types.NULL;

public class UserSQLDataAccess implements UserDAO{
    public UserSQLDataAccess() throws ResponseException, DataAccessException {
        configureDatabase();
    }

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  user (
              `username` varchar(256) NOT NULL,
              `password` varchar(256) NOT NULL,
              `email` varchar(256) NOT NULL,
              PRIMARY KEY (`username`)
            )
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
    public UserData addUserData(UserData userData) throws ResponseException {
        var statement = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
        // for game?? var json = new Gson().toJson(authData);
        int test = executeUpdate(statement,userData.getUsername(),userData.getPassword(),userData.getEmail());
        return new UserData(userData.getUsername(),userData.getPassword(),userData.getEmail());
    }

    @Override
    public Collection<UserData> listUserDatas() {
        var result = new ArrayList<UserData>();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT username FROM user";
            try (var ps = conn.prepareStatement(statement)) {
                try (var rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(readUserData(rs));
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
    public UserData getUserData(String username) throws ResponseException {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT username, password, email FROM user WHERE username=?";
            try (var ps = conn.prepareStatement(statement)) {
                ps.setString(1,username);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return readUserData(rs);
                    }
                }
            }
        }
        catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to read data: %s", e.getMessage()));
        }
        return null;
    }

    private UserData readUserData(ResultSet rs) throws SQLException {
        var username = rs.getString("username");
        var password = rs.getString("password");
        var email = rs.getString("email");
        UserData userData = new UserData(username,password,email);
        return userData;
    }

    @Override
    public void clearUserDatas() throws ResponseException{
        var statement = "TRUNCATE user";
        executeUpdate(statement);
    }
}
