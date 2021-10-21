package de.hswhameln.isbnvalidator.controller;

import de.hswhameln.isbnvalidator.dto.BookResponse;
import de.hswhameln.isbnvalidator.dto.DeleteRequest;
import de.hswhameln.isbnvalidator.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.hswhameln.isbnvalidator.dto.AddRequest;
import de.hswhameln.isbnvalidator.services.BookService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Nimmt Anfragen auf und gibt sie an den BookService weiter.
 */
@RestController
public class BookController {

    private BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    /**
     * Nimmt einen AddRequest auf und erstellt mit dem übergebenen Buch ein neues
     * Buch in der Datenbank.
     * 
     * @param request
     */
    @PostMapping(path = "/addbook")
    public void createBook(@RequestBody AddRequest request) {
        service.createBook(request.getBook());
    }

    /**
     * Leitet den Suchbefehl an den BookService weiter. Wirft, falls kein Buch
     * gefunden wurde eine BookNotFoundException.
     * 
     * @param isbn
     * @return
     */
    @GetMapping(path = "/findBook")
    public BookResponse searchBook(@RequestParam String isbn) {
        return new BookResponse(this.service.findBook(isbn).orElseThrow(() -> new BookNotFoundException(isbn)));
    }

    /**
     * Gibt den Lösch-Befehl an den BookService weiter und wirft, falls kein
     * zugehöriges Buch gefunden wurde, eine BookNotFoundException.
     * 
     * @param isbn
     */
    @DeleteMapping(path = "/deleteBook")
    public void deleteBook(@RequestParam String isbn) {
        this.service
                .deleteBooks(List.of(this.service.findBook(isbn).orElseThrow(() -> new BookNotFoundException(isbn))));
    }

    /**
     * Gibt den Lösch-Befehl an den BookService weiter und wirft, falls keine
     * zugehörigen Bücher gefunden wurden, BookNotFoundExceptions.
     * 
     * @param request
     */
    @DeleteMapping(path = "/deleteBooks")
    public void deleteBooks(@RequestBody DeleteRequest request) {
        this.service.deleteBooks(request.getIsbns().stream()
                .map(i -> this.service.findBook(i).orElseThrow(() -> new BookNotFoundException(i)))
                .collect(Collectors.toList()));
    }
}
