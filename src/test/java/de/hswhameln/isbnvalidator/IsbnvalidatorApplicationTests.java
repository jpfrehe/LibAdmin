package de.hswhameln.isbnvalidator;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.exceptions.BookNotFoundException;
import de.hswhameln.isbnvalidator.services.BookService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
		service.deleteBooks(List.of(new Book("Nina & Tom","Tom Kummer","Blumenbar","978-3-351-05035-1")));
	}

	@Disabled @Test
	void deleteBookAndSearchTest() {
		service.deleteBooks(List.of(new Book("Es existiert","Johannes Huber","Goldmann","978-3-442-22232-2")));
		assertThrows(BookNotFoundException.class, () -> service.findBook("978-3-442-22232-2").get());
	}

	@Test
	void createBookTest() {
		service.createBook(new Book("Star Wars: Das Licht der Jedi", "Charles Soule","Blanvalent","978-0-593-15771-8"));
		System.out.println(service.findBook("978-0-593-15771-8").get().getTitle());
	}

	@Test
	void findBookTest() {
		service.findBook("978-3-442-22232-2");
	}

	@Disabled @Test
	void createFalseBookTest() {
		service.createBook(new Book("Star Wars: Das Licht der Jedi", "Charles Soule","Blanvalent","978-0-593-15771-9"));
		System.out.println(service.findBook("978-0-593-15771-9").get().getTitle());
	}

	@Test
	void createAndDeleteTest() {
		Book b = new Book("SuperSache","Markus Büning", "Panini", "978-3-442-12345-2");
		service.createBook(b);
		service.deleteBooks(List.of(b));
	}

	@Disabled @Test
	void findAndDeleteTest() {
		// Book b = new Book(service.findBook("978-3-7371-0140-0"));
		// service.deleteBooks(List.of(b));
	}
}
