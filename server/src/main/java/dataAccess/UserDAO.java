package dataAccess;

import model.UserData;
import server.ResponseException;

import java.util.Collection;

public interface UserDAO {
    UserData addUserData(UserData userData) throws ResponseException;
    Collection<UserData> listUserDatas();
    UserData getUserData(String username) throws ResponseException;
    void clearUserDatas() throws ResponseException;

}
