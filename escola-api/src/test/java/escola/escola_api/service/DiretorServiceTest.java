package escola.escola_api.service;

import escola.escola_api.model.Diretor;
import escola.escola_api.repository.DiretorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiretorServiceTest {

    @Mock
    private DiretorRepository diretorRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DiretorService diretorService;

    private Diretor diretor;

    @BeforeEach
    void setUp() {
        diretor = new Diretor();
        diretor.setNome("Carlos Diretor");
        diretor.setCpf("52998224725");
        diretor.setEmail("carlos@escola.com");
        diretor.setUsername("admin");
        diretor.setSenha("senha123");
    }

    @AfterEach
    void limparContexto() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void devePermitirCadastroDoPrimeiroDiretorSemAutenticacao() {
        when(diretorRepository.count()).thenReturn(0L);
        when(diretorRepository.findByUsername("admin")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("senha123")).thenReturn("hash123");
        when(diretorRepository.save(diretor)).thenReturn(diretor);

        Diretor resultado = diretorService.cadastrar(diretor);

        assertEquals("hash123", resultado.getSenha());
    }

    @Test
    void naoDevePermitirSegundoCadastroSemAutenticacao() {
        when(diretorRepository.count()).thenReturn(1L);

        assertThrows(IllegalArgumentException.class, () -> diretorService.cadastrar(diretor));
    }

    @Test
    void devePermitirSegundoCadastroComDiretorAutenticado() {
        when(diretorRepository.count()).thenReturn(1L);
        when(diretorRepository.findByUsername("admin")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("senha123")).thenReturn("hash123");
        when(diretorRepository.save(diretor)).thenReturn(diretor);

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_DIRETOR"));
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("outroDiretor", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Diretor resultado = diretorService.cadastrar(diretor);

        assertEquals("hash123", resultado.getSenha());
    }

    @Test
    void naoDeveCadastrarComUsernameDuplicado() {
        when(diretorRepository.count()).thenReturn(0L);
        when(diretorRepository.findByUsername("admin")).thenReturn(Optional.of(diretor));

        assertThrows(IllegalArgumentException.class, () -> diretorService.cadastrar(diretor));
    }

    @Test
    void deveListarTodosOsDiretores() {
        when(diretorRepository.findAll()).thenReturn(List.of(diretor));

        List<Diretor> resultado = diretorService.listarTodos();

        assertEquals(1, resultado.size());
    }
}