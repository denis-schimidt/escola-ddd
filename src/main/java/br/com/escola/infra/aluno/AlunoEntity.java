package br.com.escola.infra.aluno;

import br.com.escola.dominio.aluno.Aluno;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.*;

@Table(name = "alunos")
@Entity(name = "aluno")
public class AlunoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 999999999)
    @Column(name="base_cpf")
    private Long baseCpf;

    @NotNull
    @Min(value = 1)
    @Max(value = 99)
    @Column(name="digitos_cpf")
    private Byte digitosCpf;

    @NotBlank
    @Size(min = 5, max = 50)
    @Column(length = 50)
    private String nome;

    @NotBlank @Email
    @Column(length = 100)
    private String email;

    @NotNull
    @OneToMany( cascade = {PERSIST, MERGE})
    @JoinColumn(name = "aluno_id")
    private List<TelefoneEntity> telefones = List.of();

    @NotBlank
    @Size(min = 5, max = 80)
    private String senha;

    // JPA Eyes
    AlunoEntity() {}

    private AlunoEntity(Aluno aluno) {
        this.baseCpf = aluno.getCpf().getBaseCpf();
        this.digitosCpf = aluno.getCpf().getDigitos();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail().getEndereco();
        this.senha = aluno.getSenha();
        this.telefones = aluno.getTelefones()
                .stream()
                .map(TelefoneEntity::new)
                .collect(toList());
    }

    public static AlunoEntity from(Aluno aluno) {
        return new AlunoEntity(aluno);
    }

    public Long getId() {
        return id;
    }

    public Long getBaseCpf() {
        return baseCpf;
    }

    public Byte getDigitosCpf() {
        return digitosCpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<TelefoneEntity> getTelefones() {
        return List.copyOf(telefones);
    }

    public String getSenha() {
        return senha;
    }
}
