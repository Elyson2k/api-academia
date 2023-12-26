package elysonvnc.projeto.academia.services;

import elysonvnc.projeto.academia.domain.dto.FaturamentoDto;
import elysonvnc.projeto.academia.domain.entities.Aluno;
import elysonvnc.projeto.academia.domain.dto.AlunoDto;
import elysonvnc.projeto.academia.enums.Status;
import elysonvnc.projeto.academia.repository.AlunoRepository;
import elysonvnc.projeto.academia.services.exceptions.AlunoException;
import elysonvnc.projeto.academia.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static elysonvnc.projeto.academia.enums.PlanoAcademia.*;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> buscandoAlunosPeloNome(String nome) {
        try {
            return alunoRepository.findByNomeContainingIgnoreCase(nome);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Error: Aluno não encontrado, por favor verifique o nome e tente novamente.");
        }
    }

    public List<Aluno> buscandoTodosAlunos(){
        List<Aluno> listaAlunos = alunoRepository.findAll();
        alterandoStatusAluno(listaAlunos);
        if(listaAlunos.isEmpty()) throw new AlunoException("Error: Não tem nenhum aluno cadastrado");
        return listaAlunos;
    }

    public FaturamentoDto faturamentoMensal(){
        List<Aluno> alunos = buscandoTodosAlunos();

        double faturamento = 00.00;
        int qtdAlunos = 0;

        for (Aluno aluno : alunos) {
            qtdAlunos += 1;
            if (aluno.getStatusAluno() == Status.ATIVO) faturamento += aluno.getValorPlano().doubleValue();
        }

        return new FaturamentoDto(qtdAlunos, faturamento);
    }

    public List<Aluno> buscandoAlunosPendentes(){
        return alunoRepository.findAlunoWhereStatusInativo();
    }

    @Transactional
    public Aluno cadastrarAluno(AlunoDto aluno){
        Aluno alunoEntity = new Aluno(null, aluno.getNome(), aluno.getPlanoAcademia());
        validandoAluno(alunoEntity);
        return alunoRepository.save(alunoEntity);
    }

    private void alterandoStatusAluno(List<Aluno> alunos) {
        LocalDate agora = LocalDate.now();
        for (int i = 0; i < alunos.size(); i++) {
            LocalDate dataFim = alunos.get(i).getDataFim();
            if (agora.isAfter(dataFim)) {
                alunos.get(i).setStatusAluno(Status.INATIVO);
            }
        }
    }

    private static void validandoAluno(Aluno aluno) {
        if(aluno.getPlanoAcademia() == MENSAL){
            aluno.setValorPlano(BigDecimal.valueOf(65.00));
            aluno.setDataFim(aluno.getDataInicio().plusMonths(1));
        } else if(aluno.getPlanoAcademia() == BIMESTRAL){
            aluno.setValorPlano(BigDecimal.valueOf(120.00));
            aluno.setDataFim(aluno.getDataInicio().plusMonths(2));
        } else if(aluno.getPlanoAcademia() == TRIMESTRAL){
            aluno.setValorPlano(BigDecimal.valueOf(170.00));
            aluno.setDataFim(aluno.getDataInicio().plusMonths(3));
        } else if(aluno.getPlanoAcademia() == ANUAL){
            aluno.setValorPlano(BigDecimal.valueOf(650.00));
            aluno.setDataFim(aluno.getDataInicio().plusMonths(12));
        }
    }


}
