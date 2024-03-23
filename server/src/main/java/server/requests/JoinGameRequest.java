package server.requests;

import chess.ChessGame;

public class JoinGameRequest {
    ChessGame.TeamColor playerColor;
    int gameID;
    String authToken;

    public JoinGameRequest(ChessGame.TeamColor playerColor, int gameID, String authToken) {
        this.playerColor = playerColor;
        this.gameID = gameID;
        this.authToken = authToken;
    }

    public ChessGame.TeamColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(ChessGame.TeamColor playerColor) {
        this.playerColor = playerColor;
    }

    public int getGameID() {
        return gameID;
    }


    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

}
