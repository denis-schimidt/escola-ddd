package br.com.escola.dominio.aluno;

import br.com.escola.dominio.commons.Evento;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class EventoAlunoMatriculado implements Evento {
    private final LocalDateTime momento;
    private final Cpf cpfDoAluno;

    public EventoAlunoMatriculado(Cpf cpfDoAluno) {
        this.momento = LocalDateTime.now();
        this.cpfDoAluno = requireNonNull(cpfDoAluno);
    }

    @Override
    public LocalDateTime momento() {
        return momento;
    }

    public Cpf getCpfDoAluno() {
        return cpfDoAluno;
    }
}
