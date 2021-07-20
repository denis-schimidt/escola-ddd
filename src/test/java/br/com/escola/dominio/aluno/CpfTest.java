package br.com.escola.dominio.aluno;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CpfTest {

    @ParameterizedTest
    @DisplayName("Check if cpf is invalid")
    @MethodSource("provideInvalidCpfs")
    public void shouldNotCreateCpfWhenCpfIsInvalid(long base, int digits) {
        assertThrows(IllegalArgumentException.class, ()-> Cpf.of(base, digits));
    }

    private static Stream<Arguments> provideInvalidCpfs() {
        return Stream.of(
                Arguments.of(970626110L, 13),
                Arguments.of(-10, 0),
                Arguments.of(333333333, 33),
                Arguments.of(227435410, -32)
        );
    }

    @Test
    @DisplayName("Should create a valid CPF")
    public void shouldCreateCpfValid() {
        Assertions.assertNotNull(Cpf.of(176389140L, 23));
    }
}