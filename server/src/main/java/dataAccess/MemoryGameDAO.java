package dataAccess;

import chess.ChessGame;
import model.GameData;

import java.util.Collection;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{
    private int nextID = 1;
    private HashMap<Integer, GameData> gameDatas = new HashMap<>();
    public GameData addGameData(String gameName) {
        ChessGame chessGame = new ChessGame();
        GameData gameData = new GameData(nextID++, null, null, gameName, chessGame);

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
}
