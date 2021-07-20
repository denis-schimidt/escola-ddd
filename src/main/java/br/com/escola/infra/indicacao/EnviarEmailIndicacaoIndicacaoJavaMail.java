package br.com.escola.infra.indicacao;

import br.com.escola.aplicacao.indicacao.EnviarEmailIndicacao;
import br.com.escola.dominio.aluno.Aluno;
import org.springframework.stereotype.Service;

@Service
public class EnviarEmailIndicacaoIndicacaoJavaMail implements EnviarEmailIndicacao {

    @Override
    public void enviarPara(Aluno indicado) {
        System.out.printf("Email de indicação enviado para o aluno %s", indicado.getNome());
    }
}
