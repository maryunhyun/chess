package dataAccess;

import model.GameData;

import java.util.Collection;

public interface GameDAO {
        GameData addGameData(String gameName);
        Collection<GameData> listGameDatas();
        GameData getGameData(int gameID);
        void deleteGameData(Integer gameID);
        void clearGameDatas();
}
