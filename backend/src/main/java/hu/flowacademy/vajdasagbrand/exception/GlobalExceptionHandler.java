package hu.flowacademy.vajdasagbrand.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(List.of(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Object> handleUserException(ValidationException ex) {
        return new ResponseEntity<>(List.of(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }
}
