package escola.escola_api.service;

import escola.escola_api.model.Diretor;
import escola.escola_api.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        if (diretorRepository.findByUsername(diretor.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Já existe um diretor cadastrado com esse username.");
        }

        diretor.setSenha(passwordEncoder.encode(diretor.getSenha()));
        return diretorRepository.save(diretor);
    }
}