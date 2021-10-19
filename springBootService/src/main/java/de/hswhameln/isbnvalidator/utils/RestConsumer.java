package de.hswhameln.isbnvalidator.utils;

import de.hswhameln.isbnvalidator.dto.ValidationResponse;
import de.hswhameln.isbnvalidator.exceptions.ISBNValidatorNotAccessibleException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestConsumer {
    private static RestConsumer instance;

    public static RestConsumer getInstance() {
        if(instance == null) {
            instance = new RestConsumer("https://isbnchecker.azurewebsites.net/api");
        }
        return instance;
    }
    
    private  final Logger logger = LoggerFactory.getLogger(RestConsumer.class);
    private String url;

    private RestConsumer(String url) {
        this.url = url;
    }

    public JSONObject postRequest(String urlPath, Map<String, String> params) {
        this.logger.info(String.format("Execute Post-Request with params <%s>", params.toString()));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url + urlPath, params, String.class);
        
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            try {
                return new JSONObject(response.getBody());
            } catch (JSONException e) {
                throw new ISBNValidatorNotAccessibleException();
            }
        } else if (response.getStatusCode().equals(HttpStatus.CONFLICT)) {
            throw new IllegalArgumentException();
        } else {
            throw new ISBNValidatorNotAccessibleException();
        }
    }

    public ValidationResponse getRequest(String urlPath, String isbn) {
        this.logger.info(String.format("Execute Get-Request with param <%s>", isbn));
         
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ValidationResponse> response = restTemplate.getForEntity(url + urlPath + "?isbn=" + isbn, ValidationResponse.class);


        this.logger.info("Received a response for the Get-Request with status: " + response.getStatusCode());
        this.logger.info("Response includes Body: " + response.getBody());
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        } else if (response.getStatusCode().equals(HttpStatus.CONFLICT)) {
            throw new IllegalArgumentException();
        } else {
            throw new ISBNValidatorNotAccessibleException();
        }
    }

}
