package de.hswhameln.isbnvalidator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Extra Advice für den Fall, dass ein ungültiges Zeichen eingegeben wurde
 */
@ControllerAdvice
public class IllegalArgumentAdvice {

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String illegalArgumentHandler(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
