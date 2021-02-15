package hu.flowacademy.vajdasagbrand.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends Exception {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e) {
        log.error("Something went wrong:", e);
        return new ResponseEntity<>(List.of(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Object> handleUserException(ValidationException ex) {
        log.error("Validation error:", ex);
        return new ResponseEntity<>(List.of(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }
}
