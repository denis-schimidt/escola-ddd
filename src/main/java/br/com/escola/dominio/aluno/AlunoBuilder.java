package br.com.escola.dominio.aluno;

import java.util.List;

public class AlunoBuilder {
    private Aluno aluno;

    private AlunoBuilder() {}

    public static AlunoBuilder builder() {
        return new AlunoBuilder();
    }

    public SemTelefone com(Cpf cpf, String nome, SenhaCriptografada senha, Email email) {
        this.aluno = new Aluno(cpf, nome, email, senha);

        return new SemTelefone();
    }

    public class SemTelefone {

        public Completo com(Telefone telefone) {
            aluno.adicionar(telefone);

            return new Completo();
        }

        public Completo com(List<Telefone> telefone) {
            telefone.forEach(aluno::adicionar);

            return new Completo();
        }
    }

    public class Completo {

        public Aluno criar() {
            return aluno;
        }
    }
}


