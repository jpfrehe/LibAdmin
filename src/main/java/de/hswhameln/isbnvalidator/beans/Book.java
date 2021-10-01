package de.hswhameln.isbnvalidator.beans;

import javax.persistence.*;

@Entity
@Table(name="T_BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
