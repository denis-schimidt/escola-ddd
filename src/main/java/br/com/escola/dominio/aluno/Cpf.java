package br.com.escola.dominio.aluno;

import br.com.caelum.stella.validation.CPFValidator;

import java.util.Objects;

import static java.lang.String.format;

public class Cpf {
    private static final CPFValidator CPF_VALIDATOR = new CPFValidator();

    private long baseCpf;
    private byte digitos;

    private Cpf(long base, int digits) {

        if (!isCpfValid(base, digits)) {
            throw new IllegalArgumentException(format("Cpf is not valid -> %d-%02d", base, digits));
        }

        this.baseCpf = base;
        this.digitos = (byte) digits;
    }

    public static Cpf of(long base, int digits) {
        return new Cpf(base, digits);
    }

    private boolean isCpfValid(long base, int digits) {
        try {
            CPF_VALIDATOR.assertValid(format("%d%02d", base, digits));

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public long getBaseCpf() {
        return baseCpf;
    }

    public byte getDigitos() {
        return digitos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf = (Cpf) o;
        return Objects.equals(baseCpf, cpf.baseCpf) && Objects.equals(digitos, cpf.digitos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseCpf, digitos);
    }

    @Override
    public String toString() {
        return format("%,d-%02d", baseCpf, digitos);
    }
}
