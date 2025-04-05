create schema if not exists test;

CREATE TABLE if not exists test.Pet (
                     id BIGINT PRIMARY KEY,
                     name VARCHAR(255),
                     owner VARCHAR(255),
                     description TEXT
);