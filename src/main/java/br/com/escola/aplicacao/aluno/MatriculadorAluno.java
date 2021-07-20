package br.com.escola.aplicacao.aluno;

import br.com.escola.dominio.aluno.AlunoRepository;
import br.com.escola.dominio.aluno.EncriptadorSenha;
import br.com.escola.infra.aluno.Sha256;
import br.com.escola.infra.commons.SpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MatriculadorAluno {

    @Autowired @SpringData
    private AlunoRepository alunoRepository;

    @Autowired @Sha256
    private EncriptadorSenha encriptador;

    @Transactional
    public void executar(MatriculaAlunoDto dto) {
        alunoRepository.matricular(dto.criarAluno(encriptador));
    }
}
