package de.hswhameln.isbnvalidator.repositories;

import de.hswhameln.isbnvalidator.beans.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
