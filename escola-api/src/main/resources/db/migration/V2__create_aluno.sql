CREATE TABLE aluno (
    id BIGINT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    nota DECIMAL(4,2),
    turma VARCHAR(50) NOT NULL,
    CONSTRAINT fk_aluno_pessoa FOREIGN KEY (id) REFERENCES pessoa(id)
);

CREATE TABLE aluno_disciplinas (
    aluno_id BIGINT NOT NULL,
    disciplina VARCHAR(100) NOT NULL,
    CONSTRAINT fk_aluno_disciplinas FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);