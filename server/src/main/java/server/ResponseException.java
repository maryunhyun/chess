package server;

public class ResponseException extends Exception {
    private int statusCode;
    private String message;

    public ResponseException(int statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public int statusCode() {
        return statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
