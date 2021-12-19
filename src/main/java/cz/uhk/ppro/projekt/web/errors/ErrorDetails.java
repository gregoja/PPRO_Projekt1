package cz.uhk.ppro.projekt.web.errors;

import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

public class ErrorDetails {
    private final Date timestamp;
    private String message;
    private int code;
    private Map<String,String> errors;

    public ErrorDetails(String message, HttpStatus httpStatus, Map<String, String> errors) {
        this.timestamp = new Date();
        this.message = message;
        this.code = httpStatus.value();
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}