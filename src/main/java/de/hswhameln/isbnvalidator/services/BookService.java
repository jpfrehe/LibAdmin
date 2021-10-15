package de.hswhameln.isbnvalidator.services;

import de.hswhameln.isbnvalidator.exceptions.BookAlreadyExistsException;
import de.hswhameln.isbnvalidator.exceptions.BookNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.hswhameln.isbnvalidator.beans.Book;
import de.hswhameln.isbnvalidator.repositories.BookRepository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void deleteBooks(List<Book> books) {
        for(Book book: books){
            this.deleteBook(book);
        }
    }

    public void deleteBook(Book book){
        if(this.repository.findByisbn(book.getIsbn()).isPresent()){
            this.repository.delete(this.repository.findByisbn(book.getIsbn()).get());
        } else{
            throw new BookNotFoundException(book.getIsbn());
        }
    }

    private boolean validateISBN(String isbn) {
        String url = "localhost:3000/validateISBN";

        Map<String, String> params = new HashMap<String, String>();
        params.put("isbn", isbn);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity( url, params, String.class );

        //BodyCall
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("email", "first.last@example.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response1 = restTemplate.postForEntity( url, request , String.class );
        return false;
    }
}
