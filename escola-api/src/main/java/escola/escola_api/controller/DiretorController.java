package escola.escola_api.controller;

import escola.escola_api.model.Diretor;
import escola.escola_api.service.DiretorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    private final DiretorService diretorService;

    @Autowired
    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @PostMapping
    public ResponseEntity<Diretor> cadastrar(@Valid @RequestBody Diretor diretor) {
        Diretor diretorSalvo = diretorService.cadastrar(diretor);
        diretorSalvo.setSenha(null); // nunca retornar a senha, nem hasheada
        return ResponseEntity.status(HttpStatus.CREATED).body(diretorSalvo);
    }
}