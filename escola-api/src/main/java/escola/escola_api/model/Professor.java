package escola.escola_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "professor ")

public class Professor extends Pessoa{
    @Column(nullable = false, unique = true, length = 20)
    private String matricula;

   


     @Column(nullable = false, length = 100)
    private String disciplina;

    @Column(nullable = false)
    private BigDecimal salario;

    public Professor() {
    }

    // Getters e Setters

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}