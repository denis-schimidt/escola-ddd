package br.com.escola.controller.aluno;

import br.com.escola.dominio.aluno.Telefone;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class TelefoneDto {
    private Integer ddd;
    private Long numero;

    public static TelefoneDto from(Telefone telefone) {
        TelefoneDto dto = new TelefoneDto();
        dto.ddd = (int) telefone.getDdd();
        dto.numero = telefone.getNumero();

        return dto;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("ddd", ddd)
                .append("numero", numero)
                .toString();
    }
}
