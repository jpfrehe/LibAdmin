drop table T_BOOK if exists;

create table T_BOOK (
    id integer identity primary key,
    title varchar(200) not null,
    author varchar(200) not null,
    publisher varchar(200) not null,
    isbn varchar(17) not null);