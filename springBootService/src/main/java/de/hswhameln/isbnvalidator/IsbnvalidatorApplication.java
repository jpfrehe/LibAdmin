package de.hswhameln.isbnvalidator;

import de.hswhameln.isbnvalidator.beans.ISBNValidationResult;
import de.hswhameln.isbnvalidator.dto.ValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class IsbnvalidatorApplication {

	private static final Logger log = LoggerFactory.getLogger(IsbnvalidatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IsbnvalidatorApplication.class, args);
	}
}
