package escola.escola_api.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aluno")
public class Aluno extends Pessoa {

    @Column(nullable = false, unique = true, length = 20)
    private String matricula;

    @Column(precision = 4, scale = 2)
    private BigDecimal nota;

    @Column(nullable = false, length = 50)
    private String turma;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "aluno_disciplinas", joinColumns = @JoinColumn(name = "aluno_id"))
    @Column(name = "disciplina")
    private List<String> disciplinas = new ArrayList<>();

    public Aluno() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public List<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }
}