package br.com.escola.dominio.aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository {

    void matricular(Aluno aluno);

    Optional<Aluno> buscarPorCpf(Cpf cpf);

    List<Aluno> listarTodosAlunosMatriculados();
}
