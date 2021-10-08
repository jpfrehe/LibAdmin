package de.hswhameln.isbnvalidator;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.exceptions.BookAlreadyExistsException;
import de.hswhameln.isbnvalidator.exceptions.BookNotFoundException;
import de.hswhameln.isbnvalidator.repositories.BookRepository;
import de.hswhameln.isbnvalidator.services.BookService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	void testDataCreated() {
		assertEquals(16,StreamSupport.stream(this.repository.findAll().spliterator(), false).count());
	}

	@Test
	void deleteBookTest() {
		service.deleteBooks(List.of(new Book("Nina & Tom","Tom Kummer","Blumenbar","978-3-351-05035-1")));
	}

	@Test
	void deleteBookAndSearchTest() {
		service.deleteBooks(List.of(new Book("Es existiert","Johannes Huber","Goldmann","978-3-442-22232-2")));
		assertEquals(Optional.empty(), service.findBook("978-3-442-22232-2"));
	}

	@Test
	void createBookTest() {
		service.createBook(new Book("Star Wars: Das Licht der Jedi", "Charles Soule","Blanvalent","978-0-593-15771-8"));
		System.out.println(service.findBook("978-0-593-15771-8").get().getTitle());
	}

	@Test
	void findBookTest() {
		assertTrue(service.findBook("978-3-442-22232-2").isPresent());
	}

	@Disabled @Test
	void createFalseBookTest() {
		service.createBook(new Book("Star Wars: Das Licht der Jedi", "Charles Soule","Blanvalent","978-0-593-15771-9"));
		System.out.println(service.findBook("978-0-593-15771-9").get().getTitle());
	}

	@Test
	void createAndDeleteTest() {
		Book b = new Book("SuperSache", "Markus Büning", "Panini", "978-3-442-12345-2");
		service.createBook(b);
		service.deleteBooks(List.of(b));
	}

	@Test
	void findAndDeleteTest() {
		Optional<Book> b = service.findBook("978-3-499-63405-5");
		service.deleteBooks(List.of(b.get()));
	}

	@Disabled @Test
	void createExistingBook() {
		//assertThrows(BookAlreadyExistsException.class, service.createBook(service.findBook("978-3-499-63405-5").get()));
	}
}
