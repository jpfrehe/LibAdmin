package de.hswhameln.isbnvalidator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Advice für den Fall, dass ein Buch bereits existiert
 */
@ControllerAdvice
public class BookAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String bookAlreadyExistsHandler(BookAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
