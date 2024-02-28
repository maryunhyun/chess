package dataAccess;

import model.UserData;

import java.util.Collection;

public interface UserDAO {
    UserData addUserData(UserData userData);
    Collection<UserData> listUserDatas();
    UserData getUserData(String username);
    void clearUserDatas();

}
