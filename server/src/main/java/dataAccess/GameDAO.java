package dataAccess;

import model.GameData;
import server.ResponseException;

import java.util.Collection;

public interface GameDAO {
        GameData addGameData(GameData gameData) throws ResponseException;
        Collection<GameData> listGameDatas();
        GameData getGameData(int gameID) throws ResponseException;
        void deleteGameData(int gameID) throws ResponseException;

        void clearGameDatas() throws ResponseException;
}
