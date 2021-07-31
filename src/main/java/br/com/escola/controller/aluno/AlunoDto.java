package br.com.escola.controller.aluno;

import br.com.escola.dominio.aluno.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

public class AlunoDto {
    private final String cpf;
    private final String nome;
    private final String email;
    private final String senha;
    private final List<TelefoneDto> telefones;

    @JsonCreator
    public AlunoDto(String cpf, String nome, String email, String senha, List<TelefoneDto> telefones) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefones = telefones;
    }

    public String getCpf() {
        return cpf;
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

    public static AlunoDto from(Aluno aluno) {
        List<TelefoneDto> telefones = aluno.getTelefones()
                .stream()
                .map(TelefoneDto::from)
                .collect(toUnmodifiableList());

        AlunoDto dto = new AlunoDto(aluno.getCpf().toString(), aluno.getNome(), aluno.getEmail().getEndereco(), null, telefones);

        return dto;
    }

    public Aluno criarAluno(String cpf, EncriptadorSenha encriptadorSenhaChecavel) {
        AlunoBuilder.SemTelefone alunoBuilder = AlunoBuilder.builder()
                .com(Cpf.from(cpf), nome, SenhaCriptografada.of(encriptadorSenhaChecavel, senha), Email.from(email));

        List<Telefone> telefones = this.telefones.stream()
                .map(telefone -> Telefone.of(telefone.getDdd(), telefone.getNumero()))
                .collect(toList());

        return alunoBuilder.com(telefones)
                .criar();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cpf", cpf)
                .append("nome", nome)
                .append("email", email)
                .append("telefones", telefones)
                .toString();
    }
}
