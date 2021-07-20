package br.com.escola.dominio.aluno;

public class SenhaCriptografada {
    private final String senha;
    private final EncriptadorSenha encriptador;

    private SenhaCriptografada(EncriptadorSenha encriptador, String senha) {
        this.encriptador = encriptador;
        this.senha = encriptador.encriptarSenha(senha);
    }

    public static SenhaCriptografada of(EncriptadorSenha encriptador, String senha) {
        return new SenhaCriptografada(encriptador, senha);
    }

    public boolean isSenhaValida(String senhaDescriptografada) {
        return encriptador.isSenhaDescriptografadaValida(senha, senhaDescriptografada);
    }

    @Override
    public String toString() {
        return senha;
    }
}
