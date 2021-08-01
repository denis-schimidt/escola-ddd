package br.com.escola.controller.aluno;

import br.com.escola.aplicacao.aluno.matricula.MatriculaDoAluno;
import br.com.escola.dominio.aluno.Aluno;
import br.com.escola.dominio.aluno.Cpf;
import br.com.escola.dominio.aluno.EncriptadorSenha;
import br.com.escola.dominio.aluno.NumeroMaximoTelefonesCadastradosException;
import br.com.escola.infra.aluno.Sha256;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@Validated
public class MatriculaResource {

    @Autowired
    private MatriculaDoAluno matriculaDoAluno;

    @Autowired
    @Sha256
    private EncriptadorSenha encriptador;

    @PostMapping("/alunos/{cpf}/matriculas")
    public ResponseEntity<Void> matricularAluno(@PathVariable @CPF String cpf, @RequestBody AlunoDto alunoDto) {

            try {
                Aluno aluno = alunoDto.criarAluno(cpf, encriptador);
                matriculaDoAluno.matricular(aluno);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();

                return ResponseEntity.created(uri).build();

            } catch (NumeroMaximoTelefonesCadastradosException e) {
                throw new ResponseStatusException(BAD_REQUEST, e.getMessage());
            }
        }

        @GetMapping("/alunos/{cpf}/matriculas")
        public ResponseEntity<AlunoDto> obterMatriculaPor (@PathVariable @CPF String cpf){
            return matriculaDoAluno.obterMatriculaPor(Cpf.from(cpf))
                    .map(aluno -> ResponseEntity.ok(AlunoDto.from(aluno)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }
