package ui;

import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.UserData;
import server.ResponseException;
import server.requests.CreateGameRequest;
import server.requests.JoinGameRequest;
import server.requests.LoginRequest;
import server.results.CreateGameIDResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Collection;

public class ServerFacade {

    private final String serverUrl;

    public ServerFacade(String url) {
        serverUrl = url;
    }


    //calling the web apis in server class
    public AuthData register(UserData userData) throws ResponseException {// make this line look the service? But calling handler in server class
        var path = "/user";
        return this.makeRequest("POST", path,null, userData, AuthData.class);
    }
//need a clear?
//    public void clearAll() throws ResponseException {
//        var path = "/db";
//        this.makeRequest("DELETE", path, null, null);
//    }

    public AuthData login(LoginRequest loginRequest) throws ResponseException {
        var path = "/session";
        return this.makeRequest("POST", path, null, loginRequest, AuthData.class);
    }

    public void logout(String authToken) throws ResponseException {
        var path = "/session";
        this.makeRequest("DELETE", path, authToken,null, null);
    }

    public Collection<GameData> listGames(String authToken) throws ResponseException {
        var path = "/game";
        record ListGameDataResponse(Collection<GameData> games) {
        }
        var response = this.makeRequest("GET", path, authToken,null, ListGameDataResponse.class);
        return response.games();
    }

    public CreateGameIDResult createGame(CreateGameRequest r) throws ResponseException {
        var path = "/game";
        //need to separate authToken from gameName for body part
        record CreateGameBody(String gameName){}
        CreateGameBody createGameBody = new CreateGameBody(r.getGameName());

        return this.makeRequest("POST", path, r.getAuthToken(),createGameBody, CreateGameIDResult.class);
    }

    public void joinGame(JoinGameRequest r) throws ResponseException {
        var path = "/game";
        this.makeRequest("PUT", path,r.getAuthToken(),r, null);
    }

    private <T> T makeRequest(String method, String path, String authorization, Object request, Class<T> responseClass) throws ResponseException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
//            if (method == "GET" | method == "DELETE") {
//                //http.setDoOutput(false);
//                http.addRequestProperty("authorization", authorization);//authorization
//            }
//            else if (method == "PUT") {
//                http.setDoOutput(true);
//                writeBody(request, http);
//                //need to also write a header here
//            }
//            else {
//                http.setDoOutput(true);
//                writeBody(request, http);
//                //need to also write a header here if authorizing
//            }
            if (authorization != null) {
                http.addRequestProperty("authorization", authorization);
            }
            if (request != null) {
                http.setDoOutput(true);
                writeBody(request, http);
            }

            http.connect();
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }


    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            //header
            http.addRequestProperty("Content-Type", "application/json");
            //body
            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, ResponseException {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            throw new ResponseException(status, "failure: " + status);
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }


    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }
}
