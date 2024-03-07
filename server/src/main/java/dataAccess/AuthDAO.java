package dataAccess;

import model.AuthData;
import server.ResponseException;

import java.util.Collection;

public interface AuthDAO {
    AuthData addAuthData(AuthData authData) throws ResponseException;
    Collection<AuthData> listAuthDatas();
    AuthData getAuthData(String authToken) throws ResponseException;
    void deleteAuthData(String authToken) throws ResponseException;
    void clearAuthDatas() throws ResponseException;
}
