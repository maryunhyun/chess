package dataAccess;

import model.GameData;

import java.util.Collection;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{
    private int nextID = 1;
    final private HashMap<Integer, GameData> gameDatas = new HashMap<>();
    public GameData addGameData(GameData gameData) {
        gameData = new GameData(nextID++, gameData.getWhiteUsername(), gameData.getBlackUsername(), gameData.getGameName(), gameData.getGame());
        gameDatas.put(gameData.getGameID(), gameData);
        return gameData;
    }

    public Collection<GameData> listGameDatas() {
        return gameDatas.values();
    }

    public GameData getGameData(int gameID) {
        return gameDatas.get(gameID);
    }

    public void deleteGameData(Integer gameID) {
        gameDatas.remove(gameID);
    }

    public void clearGameDatas() {
        gameDatas.clear();
    }
}
