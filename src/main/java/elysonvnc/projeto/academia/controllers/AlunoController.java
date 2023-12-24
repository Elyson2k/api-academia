package elysonvnc.projeto.academia.controllers;

import elysonvnc.projeto.academia.domain.dto.AlunoDto;
import elysonvnc.projeto.academia.domain.dto.FaturamentoDto;
import elysonvnc.projeto.academia.domain.entities.Aluno;
import elysonvnc.projeto.academia.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/buscar/{nome}")
    public ResponseEntity<List<Aluno>> buscandoAlunos(@PathVariable String nome){
        List<Aluno> listaAlunos = alunoService.buscandoAlunosPeloNome(nome);
        return ResponseEntity.ok(listaAlunos);
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<Aluno>> buscandoAlunosInativos(){
        List<Aluno> listaAlunosPendentes = alunoService.buscandoAlunosPendentes();
        return ResponseEntity.ok(listaAlunosPendentes);
    }

    @GetMapping("/faturamento")
    public ResponseEntity<FaturamentoDto> faturamentoMensalalmente(){
        var faturamento = alunoService.faturamentoMensal();
        return ResponseEntity.ok(faturamento);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> buscandoTodosAlunos(){
        List<Aluno> listaAlunos = alunoService.buscandoTodosAlunos();
        return ResponseEntity.ok(listaAlunos);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Void> insert(@Valid @RequestBody AlunoDto aluno){
        var id = alunoService.cadastrarAluno(aluno).getId();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }


}
