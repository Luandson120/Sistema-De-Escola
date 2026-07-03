CREATE TABLE pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    data_nascimento DATE
);

CREATE TABLE professor (
    id BIGINT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    disciplina VARCHAR(100) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_professor_pessoa FOREIGN KEY (id) REFERENCES pessoa(id)
);