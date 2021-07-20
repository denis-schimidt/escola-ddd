package br.com.escola.dominio.aluno;

import br.com.escola.infra.aluno.EncriptadorSenhaSha256;
import org.junit.jupiter.api.Test;

class AlunoBuilderTest {

    @Test
    public void shouldCreateAluno() {
         AlunoBuilder.builder()
                .com(Cpf.of(227435410L, 32), "Fulano",  SenhaCriptografada.of(new EncriptadorSenhaSha256(),"123abc456"), Email.from("fulano@gmail.com"))
                .com(Telefone.of(11, 974563225))
                .criar();
    }
}