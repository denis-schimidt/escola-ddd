package br.com.escola.dominio.aluno;

import br.com.escola.dominio.commons.Evento;
import br.com.escola.dominio.commons.Ouvinte;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class LogDeAlunoMatriculado implements Ouvinte<EventoAlunoMatriculado> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public boolean deveOuvir(Evento evento) {
        return evento instanceof EventoAlunoMatriculado;
    }

    @Override
    public void ouvir(EventoAlunoMatriculado evento) {
        System.out.println(String.format("O aluno com cpf: %s foi matriculado com sucesso em %s", evento.getCpfDoAluno().toFormattedString(),
                evento.momento().format(formatter)));
    }
}
