package br.com.escola.aplicacao.aluno.matricula;

import br.com.escola.dominio.aluno.Aluno;
import br.com.escola.dominio.aluno.AlunoRepository;
import br.com.escola.dominio.aluno.Cpf;
import br.com.escola.infra.commons.SpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MatriculaDoAluno {

    @Autowired @SpringData
    private AlunoRepository alunoRepository;

    @Transactional
    public void matricular(Aluno aluno) {
        alunoRepository.matricular(aluno);
    }

    public Optional<Aluno> obterMatriculaPor(Cpf cpf) {
        return alunoRepository.buscarPorCpf(cpf);
    }
}
