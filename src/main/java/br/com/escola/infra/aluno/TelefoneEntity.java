package br.com.escola.infra.aluno;

import br.com.escola.dominio.aluno.Telefone;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static java.lang.String.format;

@Table(uniqueConstraints={@UniqueConstraint(columnNames={"ddd", "numero"})})
@Entity(name = "telefone")
public class TelefoneEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 99)
    private Byte ddd;

    @NotNull
    @Min(value = 100)
    @Max(value = 999999999)
    private Long numero;

    // JPA Eyes
    TelefoneEntity() {}

    TelefoneEntity(Telefone telefone) {
        this.ddd = telefone.getDdd();
        this.numero = telefone.getNumero();
    }

    public Byte getDdd() {
        return ddd;
    }

    public Long getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        String telefoneFormatado = numero.toString().replaceFirst("(\\d+)(\\d{4})", "$1-$2");
        return format("(%02d) %s", ddd, telefoneFormatado);
    }
}
