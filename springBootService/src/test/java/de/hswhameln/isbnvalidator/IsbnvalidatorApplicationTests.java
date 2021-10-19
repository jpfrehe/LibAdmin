package de.hswhameln.isbnvalidator;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.exceptions.BookAlreadyExistsException;
import de.hswhameln.isbnvalidator.exceptions.BookNotFoundException;
import de.hswhameln.isbnvalidator.exceptions.ISBNNotValidException;
import de.hswhameln.isbnvalidator.repositories.BookRepository;
import de.hswhameln.isbnvalidator.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsbnvalidatorApplicationTests {

	private BookService service;
	private BookRepository repository;

	@Autowired
	public IsbnvalidatorApplicationTests(BookService service, BookRepository repository) {
		this.repository = repository;
		this.service = service;
	}

	@Test
	void contextLoads() {
	}

	@Test
	@DirtiesContext
	void testDataCreated() {
		assertEquals(16,StreamSupport.stream(this.repository.findAll().spliterator(), false).count());
	}

	@Test
	@DirtiesContext
	void deleteBookTest() {
		service.deleteBooks(List.of(new Book("Nina & Tom","Tom Kummer","Blumenbar","978-3-351-05035-1")));
	}

	@Test
	@DirtiesContext
	void deleteBookAndSearchTest() {
		service.deleteBook(new Book("Es existiert","Johannes Huber","Goldmann","978-3-442-22232-2"));
		assertEquals(Optional.empty(), service.findBook("978-3-442-22232-2"));
	}

	@Test
	@DirtiesContext
	void createBookTest() {
		service.createBook(new Book("Star Wars: Das Licht der Jedi", "Charles Soule","Blanvalent","978-0-593-15771-8"));
		System.out.println(service.findBook("978-0-593-15771-8").get().getTitle());
	}

	@Test
	@DirtiesContext
	void findBookTest() { assertTrue(service.findBook("978-3-442-22232-2").isPresent());
	}

	@Test
	@DirtiesContext
	void createFalseBookTest() {
		// Auf Jano warten
		assertThrows(ISBNNotValidException.class, () -> service.createBook(new Book("Star Wars: Das Licht der Jedi", "Charles Soule","Blanvalent","978-0-593-15771-9")));
	}

	@Test
	@DirtiesContext
	void createAndDeleteTest() {
		Book b = new Book("SuperSache", "Markus Büning", "Panini", "978-3-16-148410-0");
		service.createBook(b);
		service.deleteBook(b);
	}

	@Test
	@DirtiesContext
	void findAndDeleteTest() {
		Optional<Book> b = service.findBook("978-3-499-63405-5");
		service.deleteBook(b.get());
	}

	@Test
	@DirtiesContext
	void createExistingBook() {
		assertThrows(BookAlreadyExistsException.class, () -> {
			service.createBook(service.findBook("978-3-499-63405-5").get());
		});
	}

	@Test
	@DirtiesContext
	void deleteNonExistingBook() {
		assertThrows(BookNotFoundException.class, () -> {
			service.deleteBook(new Book("Bürgerliches Gesetzbuch", "BGH", "dtv Verlagsgesellschaft", "978-3-423-05001-2"));
		});
	}

	@Test
	@DirtiesContext
	void findNonExistingBook() {
		assertEquals(Optional.empty(), service.findBook("978-3-423-05001-2"));
	}
}
