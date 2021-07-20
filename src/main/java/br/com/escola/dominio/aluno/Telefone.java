package br.com.escola.dominio.aluno;

import static java.lang.String.format;

public class Telefone {
    private final byte ddd;
    private final long numero;

    private Telefone(int ddd, long numero) {

        validarDdd(ddd);
        validarNumero(numero);

        this.ddd = (byte) ddd;
        this.numero = numero;
    }

    public static Telefone of(int ddd, long numero) {
        return new Telefone(ddd, numero);
    }

    private void validarNumero(long numero) {
        int lenght = (int) (Math.log10(numero) + 1);

        if (numero <= 0 || lenght < 8 || lenght > 9) {
            throw new IllegalArgumentException(format("O numero %08d é inválido", numero));
        }
    }

    private void validarDdd(int ddd) {
        if (ddd <= 0 || ddd >= 99) {
            throw new IllegalArgumentException(format("O ddd %02d é inválido", ddd));
        }
    }

    public long getNumero() {
        return numero;
    }

    public byte getDdd() {
        return ddd;
    }

    @Override
    public String toString() {
        String telefoneFormatado =  String.valueOf(numero).replaceFirst("(\\d+)(\\d{4})", "$1-$2");
        return format("(%02d) %s", ddd, telefoneFormatado);
    }
}
