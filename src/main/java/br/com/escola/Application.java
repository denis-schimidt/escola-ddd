package br.com.escola;

import br.com.escola.aplicacao.aluno.matricula.MatriculaDoAluno;
import br.com.escola.controller.aluno.AlunoDto;
import br.com.escola.controller.aluno.MatriculaAlunoClientDto;
import br.com.escola.controller.aluno.TelefoneDto;
import br.com.escola.dominio.aluno.AlunoRepository;
import br.com.escola.infra.commons.SpringData;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("br.com.escola.infra")
@EnableFeignClients
public class Application implements CommandLineRunner {

    @Autowired
    private MatriculaDoAluno matriculaDoAluno;

    @Autowired @SpringData
    private AlunoRepository alunoRepository;

    @Autowired
    private MatriculaClient client;

    public static void main(String args[]) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        TelefoneDto telefoneDto1 = new TelefoneDto();
        telefoneDto1.setNumero(97556812L);
        telefoneDto1.setDdd(11);
        TelefoneDto telefoneDto2 = new TelefoneDto();
        telefoneDto2.setNumero(97556814L);
        telefoneDto2.setDdd(21);
        TelefoneDto telefoneDto3 = new TelefoneDto();
        telefoneDto3.setNumero(97556815L);
        telefoneDto3.setDdd(31);

        MatriculaAlunoClientDto dto = new MatriculaAlunoClientDto("Fulano", "fulano@gmail.com", "123bla", Arrays.asList(telefoneDto1, telefoneDto2));

        try {
            client.matricularAluno("264081968-29", dto);
            client.obterMatriculaPor("264081968-29").ifPresent(System.out::println);

        }catch (FeignException e) {
            System.out.println(e.getMessage());
        }
    }

    @FeignClient(value="localhost", url = "http://localhost:8080/alunos", decode404 = true)
    public interface MatriculaClient {

        @PostMapping(value = "/{cpf}/matriculas", consumes = "application/json",  produces = "application/json")
        void matricularAluno(@PathVariable("cpf") String cpf, @RequestBody MatriculaAlunoClientDto matriculaAlunoClientDto);

        @GetMapping(value = "/{cpf}/matriculas", consumes = "application/json",  produces = "application/json")
        Optional<AlunoDto> obterMatriculaPor(@PathVariable("cpf") String cpf);
    }
}
