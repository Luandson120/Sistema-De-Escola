package escola.escola_api.service;

import escola.escola_api.model.Aluno;
import escola.escola_api.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno();
        aluno.setNome("Ana Costa");
        aluno.setCpf("52998224725");
        aluno.setEmail("ana@escola.com");
        aluno.setMatricula("ALU001");
        aluno.setNota(new BigDecimal("8.5"));
        aluno.setTurma("9 Ano A");
    }

    @Test
    void deveCadastrarAlunoComSucesso() {
        when(alunoRepository.findByCpf(aluno.getCpf())).thenReturn(Optional.empty());
        when(alunoRepository.findByMatricula(aluno.getMatricula())).thenReturn(Optional.empty());
        when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno resultado = alunoService.cadastrar(aluno);

        assertEquals(aluno, resultado);
        verify(alunoRepository).save(aluno);
    }

    @Test
    void naoDeveCadastrarComCpfDuplicado() {
        when(alunoRepository.findByCpf(aluno.getCpf())).thenReturn(Optional.of(aluno));

        assertThrows(IllegalArgumentException.class, () -> alunoService.cadastrar(aluno));
    }

    @Test
    void deveListarTodosOsAlunos() {
        when(alunoRepository.findAll()).thenReturn(List.of(aluno));

        List<Aluno> resultado = alunoService.listarTodos();

        assertEquals(1, resultado.size());
    }

    @Test
    void deveBuscarAlunoPorId() {
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        Aluno resultado = alunoService.buscarPorId(1L);

        assertEquals(aluno, resultado);
    }

    @Test
    void deveDeletarAluno() {
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        alunoService.deletar(1L);

        verify(alunoRepository).delete(aluno);
    }
}