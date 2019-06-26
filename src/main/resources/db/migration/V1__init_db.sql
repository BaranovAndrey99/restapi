CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS product (
    id int8 not null,
    name varchar(255),
    type varchar(255),
    primary key (id)
);