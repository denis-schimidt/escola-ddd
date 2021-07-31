package br.com.escola.controller.aluno;

import java.util.List;

public class MatriculaAlunoClientDto {
    private final String nome;
    private final String email;
    private final String senha;
    private final List<TelefoneDto> telefones;

    public MatriculaAlunoClientDto(String nome, String email, String senha, List<TelefoneDto> telefones) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefones = telefones;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<TelefoneDto> getTelefones() {
        return telefones;
    }

    public String getSenha() {
        return senha;
    }
}
