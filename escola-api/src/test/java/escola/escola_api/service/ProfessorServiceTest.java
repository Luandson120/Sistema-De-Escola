package escola.escola_api.service;

import escola.escola_api.model.Professor;
import escola.escola_api.repository.ProfessorRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorService professorService;

    private Professor professor;

    @BeforeEach
    void setUp() {
        professor = new Professor();
        professor.setNome("Maria Silva");
        professor.setCpf("52998224725");
        professor.setEmail("maria@escola.com");
        professor.setMatricula("PROF001");
        professor.setDisciplina("Matematica");
        professor.setSalario(new BigDecimal("3500.00"));
    }

    @Test
    void deveCadastrarProfessorComSucesso() {
        when(professorRepository.findByCpf(professor.getCpf())).thenReturn(Optional.empty());
        when(professorRepository.findByMatricula(professor.getMatricula())).thenReturn(Optional.empty());
        when(professorRepository.save(professor)).thenReturn(professor);

        Professor resultado = professorService.cadastrar(professor);

        assertEquals(professor, resultado);
        verify(professorRepository).save(professor);
    }

    @Test
    void naoDeveCadastrarComCpfDuplicado() {
        when(professorRepository.findByCpf(professor.getCpf())).thenReturn(Optional.of(professor));

        assertThrows(IllegalArgumentException.class, () -> professorService.cadastrar(professor));
        verify(professorRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarComMatriculaDuplicada() {
        when(professorRepository.findByCpf(professor.getCpf())).thenReturn(Optional.empty());
        when(professorRepository.findByMatricula(professor.getMatricula())).thenReturn(Optional.of(professor));

        assertThrows(IllegalArgumentException.class, () -> professorService.cadastrar(professor));
    }

    @Test
    void deveListarTodosOsProfessores() {
        when(professorRepository.findAll()).thenReturn(List.of(professor));

        List<Professor> resultado = professorService.listarTodos();

        assertEquals(1, resultado.size());
    }

    @Test
    void deveBuscarProfessorPorId() {
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

        Professor resultado = professorService.buscarPorId(1L);

        assertEquals(professor, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoProfessorNaoEncontrado() {
        when(professorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> professorService.buscarPorId(99L));
    }

    @Test
    void deveDeletarProfessor() {
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

        professorService.deletar(1L);

        verify(professorRepository).delete(professor);
    }
}