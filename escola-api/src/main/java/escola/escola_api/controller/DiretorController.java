package escola.escola_api.controller;

import escola.escola_api.model.Diretor;
import escola.escola_api.service.DiretorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        diretorSalvo.setSenha(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(diretorSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Diretor>> listarTodos() {
        List<Diretor> diretores = diretorService.listarTodos();
        diretores.forEach(d -> d.setSenha(null));
        return ResponseEntity.ok(diretores);
    }
}