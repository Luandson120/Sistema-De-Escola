package escola.escola_api.security;

import escola.escola_api.model.Diretor;
import escola.escola_api.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DiretorUserDetailsService implements UserDetailsService {

    private final DiretorRepository diretorRepository;

    @Autowired
    public DiretorUserDetailsService(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Diretor diretor = diretorRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Diretor não encontrado: " + username));

        return User.builder()
                .username(diretor.getUsername())
                .password(diretor.getSenha())
                .roles("DIRETOR")
                .build();
    }
}