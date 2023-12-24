package elysonvnc.projeto.academia.domain.entities;


import elysonvnc.projeto.academia.enums.PlanoAcademia;
import elysonvnc.projeto.academia.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "tb_aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorPlano;
    @Enumerated(EnumType.STRING)
    private PlanoAcademia planoAcademia;
    @Enumerated(EnumType.STRING)
    private Status statusAluno;

    public Aluno(Integer id, String nome, PlanoAcademia planoAcademia) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = LocalDate.now();
        this.statusAluno = Status.ATIVO;
        this.planoAcademia = planoAcademia;
        criarAlunoConformePlano(this.planoAcademia);
    }

    public void criarAlunoConformePlano(PlanoAcademia planoAcademia) {
        switch (planoAcademia) {
            case MENSAL:
                setValorPlano(BigDecimal.valueOf(65.00));
                setDataFim(getDataInicio().plusMonths(1));
                break;
            case BIMESTRAL:
                setValorPlano(BigDecimal.valueOf(120.00));
                setDataFim(getDataInicio().plusMonths(2));
                break;
            case TRIMESTRAL:
                setValorPlano(BigDecimal.valueOf(170.00));
                setDataFim(getDataInicio().plusMonths(3));
                break;
            case ANUAL:
                setValorPlano(BigDecimal.valueOf(650.00));
                setDataFim(getDataInicio().plusMonths(12));
                break;
            default:
                throw new IllegalArgumentException("Plano de academia não reconhecido: " + planoAcademia);
        }
    }

}
