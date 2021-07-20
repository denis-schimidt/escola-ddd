package br.com.escola.infra.aluno;

import br.com.escola.dominio.aluno.EncriptadorSenha;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service @Sha256
public class EncriptadorSenhaSha256 implements EncriptadorSenha {

    @Override
    public String encriptarSenha(String senhaOriginal) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(senhaOriginal.getBytes("UTF-8"));
            return new BigInteger(1, messageDigest.digest()).toString(16);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
