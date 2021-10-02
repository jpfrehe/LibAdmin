package de.hswhameln.isbnvalidator;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

	@Test
	void deleteBookTest() {
		service.deleteBooks(List.of(new Book("Es existiert","Johannes Huber","Goldmann","978-3-442-22232-2")));
	}

	@Test
	void createBookTest() {
		service.createBook(new Book("Star Wars: Das Licht der Jedi", "Charles Soule","Blanvalent","978-0-593-15771-8"));
	}

	@Test
	void findBookTest() {
		service.findBook("978-3-442-22232-2");
	}

	@Test
	void createFalseBookTest() {
		service.createBook(new Book("Star Wars: Das Licht der Jedi", "Charles Soule","Blanvalent","978-0-593-15771-9"));
		System.out.println(service.findBook("978-0-593-15771-9").get().getTitle());
	}
}
