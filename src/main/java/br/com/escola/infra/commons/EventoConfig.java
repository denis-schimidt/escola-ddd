package br.com.escola.infra.commons;

import br.com.escola.dominio.commons.Ouvinte;
import br.com.escola.dominio.commons.PublicadorEventos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EventoConfig {

    @Autowired
    private List<Ouvinte> ouvintes;

    @Bean
    PublicadorEventos newPublicadorEventos() {
        return new PublicadorEventosPadrao(ouvintes);
    }
}
