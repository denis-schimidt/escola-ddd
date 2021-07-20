package br.com.escola.dominio.aluno;

import java.util.regex.Pattern;

public class Email {
    private static final String REGEX_EMAIL ="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(REGEX_EMAIL);

    private String endereco;

    private Email(String endereco) {

        if(endereco==null || !EMAIL_PATTERN.matcher(endereco).matches()) {
            throw new IllegalArgumentException("Email " + endereco + "inválido!");
        }

        this.endereco = endereco;
    }

    public static Email from(String endereco) {
        return new Email(endereco);
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return endereco;
    }
}
