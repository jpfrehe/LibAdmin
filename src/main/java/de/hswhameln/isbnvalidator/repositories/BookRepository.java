package de.hswhameln.isbnvalidator.repositories;

import de.hswhameln.isbnvalidator.beans.Book;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    public Optional<Book> findByISBN(String isbn);
}