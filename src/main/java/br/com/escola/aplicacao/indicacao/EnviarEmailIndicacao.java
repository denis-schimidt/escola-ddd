package br.com.escola.aplicacao.indicacao;

import br.com.escola.dominio.aluno.Aluno;

public interface EnviarEmailIndicacao {

    void enviarPara(Aluno indicado);
}
