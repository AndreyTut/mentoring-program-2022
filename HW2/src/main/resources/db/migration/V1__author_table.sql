drop table if exists book;
drop table if exists author;
create sequence seq_auth start with 1001;
create sequence seq_book;
create table author
(
    id   bigint
        constraint author_pkey
            primary key,
    name varchar(255)
);

