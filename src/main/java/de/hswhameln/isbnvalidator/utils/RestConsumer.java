package de.hswhameln.isbnvalidator.utils;

import de.hswhameln.isbnvalidator.exceptions.ISBNValidatorNotAccessibleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class RestConsumer {
    private String url;

    public RestConsumer(String url) {
        this.url = url;
    }

    public JSONObject postRequest(String urlPath, Map<String, String> params) {
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

    public JSONObject getRequest(String urlPath, String isbn) {
        Map<String, String> params = new HashMap<>();
        params.put("isbn", isbn);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + urlPath, String.class, params);
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

}
