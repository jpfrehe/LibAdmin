package de.hswhameln.isbnvalidator;

import de.hswhameln.isbnvalidator.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IsbnvalidatorApplicationTests {

	private BookService service;

	@Autowired
	public IsbnvalidatorApplicationTests(BookService service) {
		this.service = service;
	}

	@Test
	void contextLoads() {
	}

}
