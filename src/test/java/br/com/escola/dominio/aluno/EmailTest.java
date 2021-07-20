package br.com.escola.dominio.aluno;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @Test
    public void shouldCreateAValidEmail() {
        Email email = Email.from("fulano@gmail.com");

        assertNotNull(email);
    }

    @ParameterizedTest
    @DisplayName("Check if email is invalid")
    @ValueSource(strings = {"blá@gmail.com", "@gmail.com", "Çaaa@gmail.com", "Çaaa"})
    public void shouldThowsAnEnceptionWhenEmailIsInvalid(String emailAdress) {
        assertThrows(IllegalArgumentException.class, () -> Email.from(emailAdress));
    }
}