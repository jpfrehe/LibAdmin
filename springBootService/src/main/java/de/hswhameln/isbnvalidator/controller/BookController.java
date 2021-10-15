package de.hswhameln.isbnvalidator.controller;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.dto.BookResponse;
import de.hswhameln.isbnvalidator.dto.DeleteRequest;
import de.hswhameln.isbnvalidator.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.hswhameln.isbnvalidator.dto.AddRequest;
import de.hswhameln.isbnvalidator.services.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        return new BookResponse(this.service.findBook(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn)));
    }

    @DeleteMapping(path = "/deleteBook")
    public void deleteBook(@RequestParam String isbn) {
        this.service.deleteBooks(List.of(this.service.findBook(isbn)
                        .orElseThrow(() -> new BookNotFoundException(isbn))));
    }

    @DeleteMapping(path = "/deleteBooks")
    public void deleteBooks(@RequestBody DeleteRequest request) {
        this.service.deleteBooks(request.getIsbns().stream()
                .map(i -> this.service.findBook(i).orElseThrow(() -> new BookNotFoundException(i)))
                .collect(Collectors.toList()));
    }
}
