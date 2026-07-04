package escola.escola_api.repository;

import escola.escola_api.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long> {

    Optional<Diretor> findByUsername(String username);
}