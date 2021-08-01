package br.com.escola.dominio.aluno;

import static java.lang.String.format;

public class NumeroMaximoTelefonesCadastradosException extends RuntimeException {

    public NumeroMaximoTelefonesCadastradosException(Aluno aluno) {
        super(format("Número máximo de cadastro de telefones atingido para o aluno %s -> CPF: %s", aluno.getNome(), aluno.getCpf().toString()));
    }
}
