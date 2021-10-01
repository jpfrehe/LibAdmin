package de.hswhameln.isbnvalidator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.repositories.BookRepository;

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
     * @param titel     Titel als String
     * @param author    Autor als String
     * @param publisher Verlag als String
     * @param isbn      ISBN als String
     */
    public void createBook(Book book) {
        repository.save(book);
    }
}
