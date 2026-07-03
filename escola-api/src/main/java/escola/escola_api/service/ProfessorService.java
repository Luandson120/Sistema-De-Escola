package escola.escola_api.service;

import escola.escola_api.model.Professor;
import escola.escola_api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor cadastrar(Professor professor) {
        if (professorRepository.findByCpf(professor.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um professor cadastrado com esse CPF.");
        }

        if (professorRepository.findByMatricula(professor.getMatricula()).isPresent()) {
            throw new IllegalArgumentException("Já existe um professor cadastrado com essa matrícula.");
        }

        return professorRepository.save(professor);
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com id: " + id));
    }
    public Professor atualizar(Long id, Professor dadosAtualizados) {
    Professor professorExistente = buscarPorId(id);

    if (!professorExistente.getCpf().equals(dadosAtualizados.getCpf())) {
        professorRepository.findByCpf(dadosAtualizados.getCpf())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Já existe um professor cadastrado com esse CPF.");
                });
    }

    if (!professorExistente.getMatricula().equals(dadosAtualizados.getMatricula())) {
        professorRepository.findByMatricula(dadosAtualizados.getMatricula())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Já existe um professor cadastrado com essa matrícula.");
                });
    }

    professorExistente.setNome(dadosAtualizados.getNome());
    professorExistente.setCpf(dadosAtualizados.getCpf());
    professorExistente.setEmail(dadosAtualizados.getEmail());
    professorExistente.setTelefone(dadosAtualizados.getTelefone());
    professorExistente.setDataNascimento(dadosAtualizados.getDataNascimento());
    professorExistente.setMatricula(dadosAtualizados.getMatricula());
    professorExistente.setDisciplina(dadosAtualizados.getDisciplina());
    professorExistente.setSalario(dadosAtualizados.getSalario());

    return professorRepository.save(professorExistente);
}

public void deletar(Long id) {
    Professor professor = buscarPorId(id);
    professorRepository.delete(professor);
}
}