package br.com.escola.infra.aluno;

import br.com.escola.dominio.aluno.Aluno;
import br.com.escola.dominio.aluno.AlunoRepository;
import br.com.escola.dominio.aluno.Cpf;
import br.com.escola.infra.commons.Jpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository @Jpa
public class AlunoJpaRepository implements AlunoRepository {

    @Autowired
    private EntityManager em;
    @Autowired
    private AlunoConverter alunoConverter;

    @Override
    public void matricular(Aluno aluno) {
        AlunoEntity alunoEntity = AlunoEntity.from(aluno);

        em.persist(alunoEntity);
    }

    @Override
    public Optional<Aluno> buscarPorCpf(Cpf cpf) {

        try{
            AlunoEntity alunoEntity = em.createQuery("SELECT a FROM aluno a WHERE a.baseCpf=:baseCpf AND a.digitosCpf=:digitosCpf", AlunoEntity.class)
                    .setParameter("baseCpf", cpf.getBaseCpf())
                    .setParameter("digitosCpf", cpf.getDigitos())
                    .getSingleResult();

            return Optional.of(alunoConverter.from(alunoEntity));

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        return em.createQuery("SELECT a FROM aluno a", AlunoEntity.class)
                .getResultList()
                .stream()
                .map(alunoConverter::from)
                .collect(Collectors.toList());
    }
}
