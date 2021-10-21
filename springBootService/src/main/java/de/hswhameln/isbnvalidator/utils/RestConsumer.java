package de.hswhameln.isbnvalidator.utils;

import de.hswhameln.isbnvalidator.dto.ValidationResponse;
import de.hswhameln.isbnvalidator.exceptions.ISBNValidatorNotAccessibleException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Schnittstelle zum ISBNValidator, wo eine ISBN auf ihre Gültigkeit überprüft
 * wird.
 */
public class RestConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RestConsumer.class);
    private static final String url = "https://isbnchecker.azurewebsites.net/api/ValidateISBN";

    /**
     * Erstellt eine Validierungsanfrage für eine ISBN und gibt sie an den
     * ISBNValidator weiter. Zwischenergebnisse werden geloggt. Je nach
     * HttpStatus-Code wird entweder im positiven Fall ein ValidationRespose-Objekt
     * zurück gegeben oder im negativen Fall eine IllegalArgumenException oder eine
     * ISBNValidatorNotAccessibleException.
     * 
     * @param isbn
     * @return
     */
    public static ValidationResponse validateISBN(String isbn) {
        logger.info(String.format("Execute Get-Request with param <%s>", isbn));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ValidationResponse> response = restTemplate.getForEntity(url + "?isbn=" + isbn,
                ValidationResponse.class);

        logger.info("Received a response for the Get-Request with status: " + response.getStatusCode());
        logger.info("Response includes Body: " + response.getBody());
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        } else if (response.getStatusCode().equals(HttpStatus.CONFLICT)) {
            throw new IllegalArgumentException();
        } else {
            throw new ISBNValidatorNotAccessibleException();
        }
    }
}
