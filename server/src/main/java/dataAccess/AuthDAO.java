package dataAccess;

import model.AuthData;

import java.util.Collection;

public interface AuthDAO {
    AuthData addAuthData(AuthData authData);
    Collection<AuthData> listAuthDatas();
    AuthData getAuthData(String authToken);
    void deleteAuthData(String authToken);
    void clearAuthDatas();
}
