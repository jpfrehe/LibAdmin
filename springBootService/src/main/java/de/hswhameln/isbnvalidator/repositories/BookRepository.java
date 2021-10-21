package de.hswhameln.isbnvalidator.repositories;

import de.hswhameln.isbnvalidator.beans.Book;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Schnittstelle zur Postgres-Datenbank zur Laufzeit, über welche jegliche
 * Anfragen aus dem BookService übergeben werden
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    /**
     * Zusätzliche Methode um ein Buch nach seiner ISBN in der Datenbank zu finden
     * 
     * @param isbn
     * @return
     */
    public Optional<Book> findByisbn(String isbn);
}
