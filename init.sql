CREATE DATABASE libadmin;
 \connect libadmin;
 CREATE SCHEMA BOOK;
 CREATE TABLE BOOK.T_BOOK (
    id integer GENERATED ALWAYS AS IDENTITY primary key,
    title varchar(200) not null,
    author varchar(200) not null,
    publisher varchar(200) not null,
    isbn varchar(17) not null);