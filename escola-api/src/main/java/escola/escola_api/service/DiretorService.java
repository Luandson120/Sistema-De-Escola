package escola.escola_api.service;

import escola.escola_api.model.Diretor;
import escola.escola_api.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiretorService {

    private final DiretorRepository diretorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DiretorService(DiretorRepository diretorRepository, PasswordEncoder passwordEncoder) {
        this.diretorRepository = diretorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Diretor cadastrar(Diretor diretor) {
        boolean existeAlgumDiretor = diretorRepository.count() > 0;

        if (existeAlgumDiretor && !solicitanteEhDiretorAutenticado()) {
            throw new IllegalArgumentException("Apenas um diretor autenticado pode cadastrar outro diretor.");
        }

        if (diretorRepository.findByUsername(diretor.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Já existe um diretor cadastrado com esse username.");
        }

        diretor.setSenha(passwordEncoder.encode(diretor.getSenha()));
        return diretorRepository.save(diretor);
    }

    public List<Diretor> listarTodos() {
        return diretorRepository.findAll();
    }

    private boolean solicitanteEhDiretorAutenticado() {
        Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();

        if (autenticacao == null || !autenticacao.isAuthenticated()) {
            return false;
        }

        return autenticacao.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_DIRETOR"));
    }
}