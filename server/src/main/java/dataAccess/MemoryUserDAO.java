package dataAccess;

import model.UserData;

import java.util.Collection;
import java.util.HashMap;

public class MemoryUserDAO implements UserDAO{
    //private int nextID = 1;
    private HashMap<String, UserData> userDatas = new HashMap<>();

    public UserData addUserData(UserData userData) {
        userData = new UserData(userData.getUsername(),userData.getPassword(),userData.getEmail());
        userDatas.put(userData.getUsername(), userData);
        return userData;
    }

    public Collection<UserData> listUserDatas() {
        return userDatas.values();
    }
    public UserData getUserData(String username) {
        return userDatas.get(username);
    }
    public void clearUserDatas() {
        userDatas.clear();
    }


}
