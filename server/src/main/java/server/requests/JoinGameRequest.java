package server.requests;

import java.util.Objects;

public class JoinGameRequest {
    String ClientColor;
    int gameID;
    String authToken;

    public JoinGameRequest(String clientColor, int gameID, String authToken) {
        ClientColor = clientColor;
        this.gameID = gameID;
        this.authToken = authToken;
    }

    public String getClientColor() {
        return ClientColor;
    }

    public void setClientColor(String clientColor) {
        ClientColor = clientColor;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinGameRequest that = (JoinGameRequest) o;
        return gameID == that.gameID && Objects.equals(ClientColor, that.ClientColor) && Objects.equals(authToken, that.authToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ClientColor, gameID, authToken);
    }

    @Override
    public String toString() {
        return "JoinGameRequest{" +
                "ClientColor='" + ClientColor + '\'' +
                ", gameID=" + gameID +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
