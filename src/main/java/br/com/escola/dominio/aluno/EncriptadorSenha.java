package br.com.escola.dominio.aluno;

public interface EncriptadorSenha {

    String encriptarSenha(String senhaOriginal);

    default boolean isSenhaDescriptografadaValida(String senhaCriptografada, String senhaDescriptografada) {
        return senhaCriptografada.equals(encriptarSenha(senhaDescriptografada));
    }
}
