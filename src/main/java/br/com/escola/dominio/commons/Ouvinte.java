package br.com.escola.dominio.commons;

public interface Ouvinte<T> {

    boolean deveOuvir(Evento evento);

    void ouvir(T evento);
}
