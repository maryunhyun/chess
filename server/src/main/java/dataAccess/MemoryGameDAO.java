package dataAccess;

import model.GameData;
import server.ResponseException;

import java.util.Collection;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{
    private HashMap<Integer, GameData> gameDatas = new HashMap<>();
    public GameData addGameData(GameData gameData) {
        gameDatas.put(gameData.getGameID(), gameData);
        return gameData;
    }

    public Collection<GameData> listGameDatas() {
        return gameDatas.values();
    }

    public GameData getGameData(int gameID) {
        return gameDatas.get(gameID);
    }


    public void clearGameDatas() {
        gameDatas.clear();
    }

    public void deleteGameData(int gameID) throws ResponseException {
        gameDatas.remove(gameID);
    }
}
