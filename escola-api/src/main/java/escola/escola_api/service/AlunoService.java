package escola.escola_api.service;

import escola.escola_api.model.Aluno;
import escola.escola_api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno cadastrar(Aluno aluno) {
        if (alunoRepository.findByCpf(aluno.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com esse CPF.");
        }

        if (alunoRepository.findByMatricula(aluno.getMatricula()).isPresent()) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com essa matrícula.");
        }

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com id: " + id));
    }

    public Aluno atualizar(Long id, Aluno dadosAtualizados) {
        Aluno alunoExistente = buscarPorId(id);

        if (!alunoExistente.getCpf().equals(dadosAtualizados.getCpf())) {
            alunoRepository.findByCpf(dadosAtualizados.getCpf())
                    .ifPresent(a -> {
                        throw new IllegalArgumentException("Já existe um aluno cadastrado com esse CPF.");
                    });
        }

        if (!alunoExistente.getMatricula().equals(dadosAtualizados.getMatricula())) {
            alunoRepository.findByMatricula(dadosAtualizados.getMatricula())
                    .ifPresent(a -> {
                        throw new IllegalArgumentException("Já existe um aluno cadastrado com essa matrícula.");
                    });
        }

        alunoExistente.setNome(dadosAtualizados.getNome());
        alunoExistente.setCpf(dadosAtualizados.getCpf());
        alunoExistente.setEmail(dadosAtualizados.getEmail());
        alunoExistente.setTelefone(dadosAtualizados.getTelefone());
        alunoExistente.setDataNascimento(dadosAtualizados.getDataNascimento());
        alunoExistente.setMatricula(dadosAtualizados.getMatricula());
        alunoExistente.setNota(dadosAtualizados.getNota());
        alunoExistente.setTurma(dadosAtualizados.getTurma());
        alunoExistente.setDisciplinas(dadosAtualizados.getDisciplinas());

        return alunoRepository.save(alunoExistente);
    }

    public void deletar(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}