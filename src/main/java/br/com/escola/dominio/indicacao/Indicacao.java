package br.com.escola.dominio.indicacao;

import br.com.escola.dominio.aluno.Aluno;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class Indicacao {
    private Aluno indicado;
    private Aluno indicante;
    private LocalDateTime dataIndicacao;

    public Indicacao(Aluno indicado, Aluno indicante, LocalDateTime dataIndicacao) {
        this.indicado = requireNonNull(indicado);
        this.indicante = requireNonNull(indicante);
        this.dataIndicacao = dataIndicacao;
    }

    public Aluno getIndicado() {
        return indicado;
    }

    public Aluno getIndicante() {
        return indicante;
    }

    public LocalDateTime getDataIndicacao() {
        return dataIndicacao;
    }
}
