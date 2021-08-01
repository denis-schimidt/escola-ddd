package br.com.escola.infra.commons;

import br.com.escola.dominio.commons.Evento;
import br.com.escola.dominio.commons.Ouvinte;
import br.com.escola.dominio.commons.PublicadorEventos;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class PublicadorEventosPadrao implements PublicadorEventos {
    private List<Ouvinte> ouvintes;

    public PublicadorEventosPadrao(List<Ouvinte> ouvintes) {
        this.ouvintes = requireNonNull(ouvintes);
    }

    public void publicar(Evento evento) {
        ouvintes.stream()
            .filter(ouvinte -> ouvinte.deveOuvir(evento))
            .forEach(ouvinte -> ouvinte.ouvir(evento));
    }
}
