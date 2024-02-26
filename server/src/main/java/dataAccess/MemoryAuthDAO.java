package dataAccess;

import model.AuthData;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO{
    final private HashMap<String, AuthData> authDatas = new HashMap<>();

    public AuthData addAuthData(AuthData authData) {
        authData = new AuthData(UUID.randomUUID().toString(),authData.getUsername());
        authDatas.put(authData.getAuthToken(),authData);
        return authData;
    }

    public Collection<AuthData> listAuthDatas() {
        return authDatas.values();
    }

    public AuthData getAuthData(String authToken) {
        return authDatas.get(authToken);
    }

    public void deleteAuthData(String authToken) {
        authDatas.remove(authToken);
    }

    public void clearAuthDatas() {
        authDatas.clear();
    }
}
