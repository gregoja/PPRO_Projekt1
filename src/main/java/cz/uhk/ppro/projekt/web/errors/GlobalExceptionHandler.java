package cz.uhk.ppro.projekt.web.errors;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.InsufficientResourcesException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleGlobalException(RuntimeException e) {
        //e.printStackTrace();
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("error", e.toString());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ErrorDetails("INTERNAL SERVER ERROR", status, errors), status);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleEmptyResultDataException(EmptyResultDataAccessException e) {
        //e.printStackTrace();
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("error", e.toString());
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ErrorDetails("NOT FOUND", status, errors), status);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e) {
        //e.printStackTrace();
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("error", e.toString());
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ErrorDetails("NOT FOUND", status, errors), status);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleInsufficientResourcesException(InsufficientResourcesException e) {
        //e.printStackTrace();
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("error", e.toString());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorDetails("BAD REQUEST", status, errors), status);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException e) {
        //e.printStackTrace();
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorDetails("VALIDATION ERROR", status, errors), status);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("error", e.toString());
        HttpStatus status = HttpStatus.CONFLICT;
        return new ResponseEntity<>(new ErrorDetails("CONFLICT ERROR", status, errors), status);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("error", e.toString());
        HttpStatus status = HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(new ErrorDetails("U??ivatelsk?? jm??no nespl??uje po??adavky", status, errors), status);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleInvalidPasswordException(InvalidPasswordException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("error", e.toString());
        HttpStatus status = HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(new ErrorDetails("Heslo nespl??uje po??adavky", status, errors), status);
    }
}