package br.com.escola;

import br.com.escola.aplicacao.aluno.MatriculaAlunoDto;
import br.com.escola.aplicacao.aluno.MatriculadorAluno;
import br.com.escola.aplicacao.aluno.TelefoneDto;
import br.com.escola.dominio.aluno.AlunoRepository;
import br.com.escola.dominio.aluno.Cpf;
import br.com.escola.infra.commons.SpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories("br.com.escola.infra")
public class Application implements CommandLineRunner {

    @Autowired
    private MatriculadorAluno matriculadorAluno;

    @Autowired @SpringData
    private AlunoRepository alunoRepository;

    public static void main(String args[]) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        TelefoneDto telefoneDto = new TelefoneDto();
        telefoneDto.setDdd(11);
        telefoneDto.setNumero(997559652L);

        MatriculaAlunoDto dto = new MatriculaAlunoDto();
        dto.setNome("Fulano");
        dto.setBaseCpf(411219640L);
        dto.setDigitosCpf((byte)79);
        dto.setEmail("fulano@gmail.com");
        dto.setSenha("123bla789");
        dto.setTelefones(Arrays.asList(telefoneDto));

        matriculadorAluno.executar(dto);

        alunoRepository.buscarPorCpf(Cpf.of(264081968L, 29)).ifPresent(System.out::println);
    }
}
