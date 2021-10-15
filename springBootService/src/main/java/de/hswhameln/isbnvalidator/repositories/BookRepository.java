package de.hswhameln.isbnvalidator.repositories;

import de.hswhameln.isbnvalidator.beans.Book;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    public Optional<Book> findByisbn(String isbn);
}
