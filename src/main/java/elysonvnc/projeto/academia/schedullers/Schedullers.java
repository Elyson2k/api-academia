package elysonvnc.projeto.academia.schedullers;

import elysonvnc.projeto.academia.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class Schedullers {

    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private AlunoService alunoService;

    @Scheduled(cron = "0 0 0 * * ?", zone = TIME_ZONE)
    public void run(){
        alunoService.buscandoTodosAlunos();
    }
}
