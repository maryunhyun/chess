package results;

import java.util.Objects;

public class LoginResult {
    String message;
    Boolean success;

    public LoginResult(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResult that = (LoginResult) o;
        return Objects.equals(message, that.message) && Objects.equals(success, that.success);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, success);
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}