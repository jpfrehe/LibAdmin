package de.hswhameln.isbnvalidator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.repositories.BookRepository;

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
     * @param isbn      ISBN als String
     * @param titel     Titel als String
     * @param author    Autor als String
     * @param publisher Verlag als String
     * @return Bookobjekt
     */
    public Book findBook(String isbn, String titel, String author, String publisher) {
        // BookValidation.checkBook(isbn);

        return repository.findByISBN(isbn).orElseGet(() -> new Book(isbn, titel, author, publisher));
    }

    /**
     * @param isbn      ISBN als String
     * @param titel     Titel als String
     * @param author    Autor als String
     * @param publisher Verlag als String
     * @return Bookobjekt
     */
    public void createBook(String isbn, String titel, String author, String publisher) {
        repository.save(new Book(isbn, titel, author, publisher));
    }
}
