package br.com.escola.infra.aluno;

import br.com.escola.dominio.aluno.Aluno;
import br.com.escola.dominio.aluno.AlunoRepository;
import br.com.escola.dominio.aluno.Cpf;
import br.com.escola.infra.commons.InMemory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository @InMemory
public class AlunoInMemoryRepository implements AlunoRepository {

    private static Map<Cpf, Aluno> DATABASE_IN_MEMORY = new HashMap();

    @Override
    public void matricular(Aluno aluno) {
        DATABASE_IN_MEMORY.put(aluno.getCpf(), aluno);
    }

    @Override
    public Optional<Aluno> buscarPorCpf(Cpf cpf) {
        return Optional.ofNullable(DATABASE_IN_MEMORY.get(cpf));
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        return DATABASE_IN_MEMORY.values().stream().collect(toList());
    }
}
