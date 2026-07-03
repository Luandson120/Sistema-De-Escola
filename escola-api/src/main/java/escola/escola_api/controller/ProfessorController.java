package escola.escola_api.controller;

import escola.escola_api.model.Professor;
import escola.escola_api.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<Professor> cadastrar(@Valid @RequestBody Professor professor) {
        Professor professorSalvo = professorService.cadastrar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        List<Professor> professores = professorService.listarTodos();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        Professor professor = professorService.buscarPorId(id);
        return ResponseEntity.ok(professor);
    }
}