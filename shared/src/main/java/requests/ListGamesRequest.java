package requests;

import java.util.Objects;

public class ListGamesRequest {
    String authToken;

    public ListGamesRequest(String authToken) {
        this.authToken = authToken;
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
        ListGamesRequest that = (ListGamesRequest) o;
        return Objects.equals(authToken, that.authToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authToken);
    }

    @Override
    public String toString() {
        return "ListGamesRequest{" +
                "authToken='" + authToken + '\'' +
                '}';
    }
}
