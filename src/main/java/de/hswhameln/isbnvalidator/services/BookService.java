package de.hswhameln.isbnvalidator.services;

import de.hswhameln.isbnvalidator.exceptions.BookAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    // Durch Autowired im Konstruktor wird durch Spring automatisch injected
    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    /**
     * Sucht uns das Buch aus der DB oder erstellt ein neues Objekt Mit Hilfe einer
     * automatisierten WHERE Bedingung im Repository
     * 
     * @param titel     Titel als String
     * @param author    Autor als String
     * @param publisher Verlag als String
     * @param isbn      ISBN als String
     * @return Bookobjekt
     */
    public Optional<Book> findBook(String isbn) {
        // BookValidation.checkBook(isbn);

        return repository.findByisbn(isbn);
    }

    /**
     * @param book Book as Entitdy
     */
    public void createBook(Book book) {
        if(this.repository.findByisbn(book.getIsbn()).isPresent()) {
            throw new BookAlreadyExistsException(book.getIsbn());
        } else {
            this.repository.save(book);
        }
    }

    public void deleteBook(List<Book> books) {
        this.repository.deleteAll(books);
    }
}
