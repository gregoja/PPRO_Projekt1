package cz.uhk.ppro.projekt.web.errors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleGlobalException(RuntimeException e) {
        //e.printStackTrace();
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("error",e.toString());
        return new ResponseEntity<ErrorDetails>(new ErrorDetails("BAD REQUEST",errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleEmptyResultDataException(EmptyResultDataAccessException e) {
        //e.printStackTrace();
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("error",e.toString());
        return new ResponseEntity<ErrorDetails>(new ErrorDetails("NOT FOUND",errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException e) {
        //e.printStackTrace();
        Map<String,String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<ErrorDetails>(new ErrorDetails("VALIDATION ERROR",errors), HttpStatus.BAD_REQUEST);
    }
}