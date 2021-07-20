package br.com.escola.aplicacao.aluno;

import br.com.escola.dominio.aluno.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MatriculaAlunoDto {
    private Long baseCpf;
    private Byte digitosCpf;
    private String nome;
    private String email;
    private String senha;
    private List<TelefoneDto> telefones;

    public Long getBaseCpf() {
        return baseCpf;
    }

    public void setBaseCpf(Long baseCpf) {
        this.baseCpf = baseCpf;
    }

    public Byte getDigitosCpf() {
        return digitosCpf;
    }

    public void setDigitosCpf(Byte digitosCpf) {
        this.digitosCpf = digitosCpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<TelefoneDto> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneDto> telefones) {
        this.telefones = telefones;
    }

    public Aluno criarAluno(EncriptadorSenha encriptadorSenhaChecavel) {
        AlunoBuilder.SemTelefone alunoBuilder = AlunoBuilder.builder()
                .com(Cpf.of(baseCpf, digitosCpf), nome, SenhaCriptografada.of(encriptadorSenhaChecavel, senha), Email.from(email));

        List<Telefone> telefones = this.telefones.stream()
                .map(telefone -> Telefone.of(telefone.getDdd(), telefone.getNumero()))
                .collect(toList());

        return alunoBuilder.com(telefones)
                .criar();
    }
}
