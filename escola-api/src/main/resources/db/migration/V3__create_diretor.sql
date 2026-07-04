CREATE TABLE diretor (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    CONSTRAINT fk_diretor_pessoa FOREIGN KEY (id) REFERENCES pessoa(id)
);