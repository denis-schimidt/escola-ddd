package br.com.escola.dominio.aluno;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class Aluno {
    private Cpf cpf;
    private String nome;
    private Email email;
    private List<Telefone> telefones;
    private String senha;

    public Aluno(Cpf cpf, String nome, Email email, SenhaCriptografada senhaCriptografada, Telefone... telefones) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senhaCriptografada.toString();
        this.telefones = telefones.length > 0 ? asList(telefones) : new ArrayList();
    }

    public void adicionar(Telefone telefone) {

        if(telefones.size() == 2) {
            throw new NumeroMaximoTelefonesCadastradosException(this);
        }

        telefones.add(telefone);
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Email getEmail() {
        return email;
    }

    public List<Telefone> getTelefones() {
        return List.copyOf(telefones);
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, JSON_STYLE);
    }
}
