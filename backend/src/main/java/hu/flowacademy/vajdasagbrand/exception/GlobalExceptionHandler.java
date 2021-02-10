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

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Exception> handleValidationException(ValidationException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
