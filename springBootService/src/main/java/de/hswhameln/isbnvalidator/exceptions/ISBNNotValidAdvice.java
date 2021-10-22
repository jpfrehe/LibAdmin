package de.hswhameln.isbnvalidator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Advice für den Fall, dass eine ISBN nicht valide ist
 */
@ControllerAdvice
public class ISBNNotValidAdvice {

    /**
     * @param ex
     * @return String
     */
    @ResponseBody
    @ExceptionHandler(ISBNNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String isbnNotValidHandler(ISBNNotValidException ex) {
        return ex.getMessage();
    }
}
