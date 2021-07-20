package br.com.escola.infra.aluno;

import br.com.escola.dominio.aluno.Aluno;
import br.com.escola.dominio.aluno.AlunoRepository;
import br.com.escola.dominio.aluno.Cpf;
import br.com.escola.infra.commons.SpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository @SpringData
public class AlunoSpringDataRepository implements AlunoRepository {

    @Autowired
    private AlunoSpringDataRepositoryImpl repositoryImpl;
    @Autowired
    private AlunoConverter alunoConverter;

    @Override
    public void matricular(Aluno aluno) {
        AlunoEntity alunoEntity = AlunoEntity.from(aluno);

        repositoryImpl.save(alunoEntity);
    }

    @Override
    public Optional<Aluno> buscarPorCpf(Cpf cpf) {

        return repositoryImpl.findByBaseCpfAndDigitosCpf(cpf.getBaseCpf(), cpf.getDigitos())
                .map(alunoEntity -> Optional.of(alunoConverter.from(alunoEntity)))
                .orElseGet(() -> Optional.empty());
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        return repositoryImpl.findAll()
                .stream()
                .map(alunoConverter::from)
                .collect(toList());
    }
}


@Repository
interface AlunoSpringDataRepositoryImpl extends CrudRepository<AlunoEntity, Long> {

    Optional<AlunoEntity> findByBaseCpfAndDigitosCpf(Long baseCpf, Byte digitosCpf);

    List<AlunoEntity> findAll();
}