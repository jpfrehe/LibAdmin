package de.hswhameln.isbnvalidator.services;

import de.hswhameln.isbnvalidator.exceptions.BookAlreadyExistsException;
import de.hswhameln.isbnvalidator.exceptions.BookNotFoundException;
import de.hswhameln.isbnvalidator.exceptions.ISBNNotValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.repositories.BookRepository;
import de.hswhameln.isbnvalidator.utils.ISBNAPI;

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
     * @param isbn      ISBN als String
     * @return Bookobjekt
     */
    public Optional<Book> findBook(String isbn) {
        if(!ISBNAPI.validateISBN(isbn)) {
            throw new ISBNNotValidException(isbn);
        }
        return repository.findByisbn(isbn);
    }

    /**
     * Fügt ein neues Buch in der DB hinzu.
     * Dabei wird geprüft ob diese Buch bereits in der DB existiert
     * und ob die ISBN des Buches korrekt ist.
     * In diesem Fall wird ein Fehler ausgegeben.
     *
     * @param book Book as Entitdy
     */
    public void createBook(Book book) {
        if(!ISBNAPI.validateISBN(book.getIsbn())) {
            throw new ISBNNotValidException(book.getIsbn());
        }
        if(this.repository.findByisbn(book.getIsbn()).isPresent()) {
            throw new BookAlreadyExistsException(book.getIsbn());
        }
        this.repository.save(book);
    }

    /**
     * Löscht eine größere Anzahl an Büchern, welche in Form einer Liste übergeben werden.
     * Dafür ruft die Methode den Löschvorgang für ein einzelnes Buch per Schleife auf.
     *
     * @param books
     */
    public void deleteBooks(List<Book> books) {
        for(Book book: books){
            this.deleteBook(book);
        }
    }

    /**
     * Löscht ein übergebenes Buch aus der DB.
     * Dabei wird geprüft, ob das Buch in der DB exisitiert.
     * Falls nict wird ein Fheler ausgegeben.
     *
     * @param book
     */
    public void deleteBook(Book book){
        if(this.repository.findByisbn(book.getIsbn()).isPresent()){
            this.repository.delete(this.repository.findByisbn(book.getIsbn()).get());
        } else{
            throw new BookNotFoundException(book.getIsbn());
        }
    }
}
