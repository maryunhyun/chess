package server.results;

public class RegisterResult {
    String message;
    //int status;
    //with success, username and authToken given

    public RegisterResult( String message) {
        this.message = message;
        //this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
