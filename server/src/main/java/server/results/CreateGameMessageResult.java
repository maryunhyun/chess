package server.results;

public class CreateGameMessageResult {
    String message;


    public CreateGameMessageResult(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
