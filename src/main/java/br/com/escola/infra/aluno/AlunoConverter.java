package br.com.escola.infra.aluno;

import br.com.escola.dominio.aluno.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlunoConverter {

    @Autowired @Sha256
    private EncriptadorSenha encriptador;

    public Aluno from(AlunoEntity alunoEntity) {
        Cpf cpf = Cpf.of(alunoEntity.getBaseCpf(), alunoEntity.getDigitosCpf().intValue());

        List<Telefone> telefones = alunoEntity.getTelefones()
                .stream()
                .map(telefoneEntity -> Telefone.of(telefoneEntity.getDdd(), telefoneEntity.getNumero()))
                .collect(Collectors.toList());

        return new Aluno(cpf, alunoEntity.getNome(), Email.from(alunoEntity.getEmail()), SenhaCriptografada.of(encriptador, alunoEntity.getSenha()),
                telefones.toArray(Telefone[]::new));
    }
}
