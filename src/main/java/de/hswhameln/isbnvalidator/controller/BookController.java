package de.hswhameln.isbnvalidator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.hswhameln.isbnvalidator.dto.AddRequest;
import de.hswhameln.isbnvalidator.services.BookService;

@RestController
public class BookController {

    private BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping(path = "/addbook")
    public void createBook(@RequestBody AddRequest request) {
        service.createBook(request.getBook().getIsbn(), request.getBook().getTitle(), request.getBook().getAuthor(), request.getBook().getPublisher());
    }

}
