package server;

public class ResponseException extends Exception {
    private int statusCode;
    private String message;

    public ResponseException(int statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public int StatusCode() {
        return statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
