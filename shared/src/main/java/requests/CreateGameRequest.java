package requests;

import java.util.Objects;

public class CreateGameRequest {
    String gameName;
    String authToken;

    public CreateGameRequest(String gameName, String authToken) {
        this.gameName = gameName;
        this.authToken = authToken;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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
        CreateGameRequest that = (CreateGameRequest) o;
        return Objects.equals(gameName, that.gameName) && Objects.equals(authToken, that.authToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameName, authToken);
    }

    @Override
    public String toString() {
        return "CreateGameRequest{" +
                "gameName='" + gameName + '\'' +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
