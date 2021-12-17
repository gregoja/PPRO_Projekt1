package cz.uhk.ppro.projekt.web.errors;

import com.google.gson.Gson;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleGlobalException(RuntimeException e) {
        //e.printStackTrace();
        return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),"BAD REQUEST",e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleEmptyResultDataException(EmptyResultDataAccessException e) {
        //e.printStackTrace();
        return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),"NOT FOUND",e.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException e) {
        //e.printStackTrace();
        // vezme vsechny errory a misto nich uchova jen .getDefaultMessage a da to do Listu Stringu
        List<String> defaultErrorStrings = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        String json = new Gson().toJson(defaultErrorStrings, List.class);
        return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),"VALIDATION ERROR",json), HttpStatus.BAD_REQUEST);
    }
}