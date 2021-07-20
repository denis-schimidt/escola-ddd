package br.com.escola.dominio.aluno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelefoneTest {

    @Test
    public void shouldNotCreateTelephoneWhenDDDIsLessThan0() {
        assertThrows(IllegalArgumentException.class, ()-> Telefone.of(-1, 997562365));
    }

    @Test
    public void shouldNotCreateTelephoneWhenDDDIsGreaterThan99() {
        assertThrows(IllegalArgumentException.class, ()-> Telefone.of(100, 997562365));
    }

    @Test
    public void shouldNotCreateTelefoneWhenNumberIsLessThan0() {
        assertThrows(IllegalArgumentException.class, ()-> Telefone.of(11, -997562365));
    }

    @Test
    public void shouldNotCreateTelephoneWhenNumberHasMoreThan9Digits() {
        assertThrows(IllegalArgumentException.class, ()-> Telefone.of(11, 9997562365L));
    }

    @Test
    public void shouldCreateAValidTelefone() {
        assertNotNull(Telefone.of(11, 997562365));
    }
}