package de.hswhameln.isbnvalidator.controller;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.dto.BookResponse;
import de.hswhameln.isbnvalidator.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.hswhameln.isbnvalidator.dto.AddRequest;
import de.hswhameln.isbnvalidator.services.BookService;

import java.util.Optional;

@RestController
public class BookController {

    private BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping(path = "/addbook")
    public void createBook(@RequestBody AddRequest request) {
        service.createBook(request.getBook());
    }

    @GetMapping(path = "/findBook")
    public BookResponse searchBook(@RequestParam String isbn) {
        return new BookResponse(service.findBook(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn)));
    }

}
