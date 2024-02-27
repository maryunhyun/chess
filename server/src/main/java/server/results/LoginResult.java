package server.results;

public class LoginResult {
    String message;

    //with success, username and authToken given

    public LoginResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
