package elysonvnc.projeto.academia;

import elysonvnc.projeto.academia.domain.entities.Aluno;
import elysonvnc.projeto.academia.enums.PlanoAcademia;
import elysonvnc.projeto.academia.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class AcademiaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}

	@Autowired
	AlunoRepository alunoRepository;

	@Override
	public void run(String... args) throws Exception {

		Aluno aluno1 = new Aluno(null, "Elyson", PlanoAcademia.MENSAL);
		Aluno aluno2 = new Aluno(null, "Erick", PlanoAcademia.BIMESTRAL);
		Aluno aluno3 = new Aluno(null, "Kelvin", PlanoAcademia.ANUAL);
		Aluno aluno4 = new Aluno(null, "Pereira", PlanoAcademia.TRIMESTRAL);
		Aluno aluno5 = new Aluno(null, "Max", PlanoAcademia.MENSAL);

		alunoRepository.save(aluno1);
		alunoRepository.save(aluno2);
		alunoRepository.save(aluno3);
		alunoRepository.save(aluno4);
		alunoRepository.save(aluno5);
	}
}
